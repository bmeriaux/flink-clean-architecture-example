package com.example.demo.job.infrastructure.flink.source;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.URI;

@Component
public class FileSource implements Serializable {

    private final StreamExecutionEnvironment streamExecutionEnvironment;

    public FileSource(StreamExecutionEnvironment streamExecutionEnvironment) {
        this.streamExecutionEnvironment = streamExecutionEnvironment;
    }

    public DataStream<String> getStreamFromFile(String filePath) {
        return streamExecutionEnvironment.readTextFile(filePath);
    }
}
