package com.example.demo.job.infrastructure.flink;

import org.apache.flink.api.common.JobExecutionResult;

import java.io.Serializable;

public interface Job extends Serializable {
    JobExecutionResult start() throws Exception;

    String getName();
}
