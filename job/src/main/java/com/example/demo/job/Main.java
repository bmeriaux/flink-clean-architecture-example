package com.example.demo.job;


import com.example.demo.job.infrastructure.config.AppConfig;
import com.example.demo.job.infrastructure.flink.Job;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = loadSpringContext(args);
        registerArgsAsJobParameter(applicationContext, args);
        startJob(applicationContext);
    }

    private static ApplicationContext loadSpringContext(String[] args) {
        PropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().getPropertySources().addFirst(commandLinePropertySource);
        context.register(AppConfig.class);
        context.refresh();
        return context;
    }

    private static void registerArgsAsJobParameter(ApplicationContext context, String[] args) {
        ParameterTool parameters = ParameterTool.fromArgs(args);
        final ExecutionEnvironment executionEnvironment = context.getBean(ExecutionEnvironment.class);
        executionEnvironment.getConfig().setGlobalJobParameters(parameters);
    }

    private static void startJob(ApplicationContext context) throws Exception {
        String jobName = context.getEnvironment().getProperty("job.args.jobName");
        Map<String, Job> jobs = context.getBeansOfType(Job.class);
        Job jobToStart = jobs.values().stream()
            .filter(job -> job.getName().equals(jobName))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("invalid job Name"));
        jobToStart.start();
    }
}
