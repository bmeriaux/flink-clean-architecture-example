package com.example.demo.job.infrastructure.config;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ComponentScan(basePackages = {
    "com.example.demo.core",
    "com.example.demo.job",
})
public class AppConfig {

    @Bean
    public StreamExecutionEnvironment getStreamExecutionEnvironment() {
        return StreamExecutionEnvironment.getExecutionEnvironment();
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}
