package com.example.demo.application.usecase;

import com.example.demo.application.domain.JobSubmitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class TriggerWordCount {

    public static final String FILE_NAME = "loremIpsum.txt";
    private final JobSubmitter jobSubmitter;
    private final String fileRootPath;

    public TriggerWordCount(JobSubmitter jobSubmitter, @Value("${jobs.wordCount.filesRootPath}") String fileRootPath) {
        this.jobSubmitter = jobSubmitter;
        this.fileRootPath = fileRootPath;
    }

    public void trigger() throws Exception {
        Map<String, String> jobArgs = new HashMap<>();
        jobArgs.put("inputFilePath", Paths.get(fileRootPath, FILE_NAME).toString());
        jobSubmitter.submitJob("wordCount", jobArgs);
    }
}
