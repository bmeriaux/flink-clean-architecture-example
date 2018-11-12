package com.example.demo.job.infrastructure.flink.source;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class FileSource implements Serializable {

    private final ExecutionEnvironment executionEnvironment;

    public FileSource(ExecutionEnvironment executionEnvironment) {
        this.executionEnvironment = executionEnvironment;
    }

    public DataSet<String> getLinesFromFile(String filePath) {
        return executionEnvironment.readTextFile(filePath);
    }
}
