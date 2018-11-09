package com.example.demo.application.domain;

import java.util.Map;

public interface JobSubmitter {

    String submitJob(String jobName, Map<String, String> jobArgs) throws Exception;

    String submitJobDetached(String jobName, Map<String, String> jobArgs) throws Exception;
}
