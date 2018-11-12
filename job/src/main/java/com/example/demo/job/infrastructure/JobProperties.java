package com.example.demo.job.infrastructure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class JobProperties implements Serializable {

    @Value("${job.args.jobName}")
    private String jobName;

    @Value("${job.args.outputFilePath}")
    private String outputFilePath;

    @Value("${job.args.inputFilePath}")
    private String inputFilePath;
}
