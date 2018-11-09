package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.job.infrastructure.flink.Job;
import com.example.demo.job.infrastructure.flink.source.FileSource;
import com.example.demo.job.infrastructure.flink.task.TokenizeWordTask;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WordCountJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordCountJob.class);
    public static final String JOB_NAME = "wordCount";

    private final FileSource fileSource;
    private final TokenizeWordTask tokeniseWordTask;
    private final StreamExecutionEnvironment streamExecutionEnvironment;
    private final String inputFilePath;

    public WordCountJob(FileSource fileSource, TokenizeWordTask tokeniseWordTask, StreamExecutionEnvironment streamExecutionEnvironment, @Value("${job.args.inputFilePath}") String inputFilePath) {
        this.fileSource = fileSource;
        this.tokeniseWordTask = tokeniseWordTask;
        this.streamExecutionEnvironment = streamExecutionEnvironment;
        this.inputFilePath = inputFilePath;
    }

    @Override
    public JobExecutionResult start() throws Exception {
        fileSource.getStreamFromFile(inputFilePath)
            .flatMap(tokeniseWordTask)
            .keyBy(new WordTokenKeySelector())
            .sum("count")
            .print(); // TODO sink in jdbc

        LOGGER.info("job execution plan:{}", streamExecutionEnvironment.getExecutionPlan());
        return streamExecutionEnvironment.execute(JOB_NAME);
    }

    @Override
    public String getName() {
        return JOB_NAME;
    }

}
