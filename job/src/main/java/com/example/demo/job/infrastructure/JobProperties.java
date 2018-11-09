package com.example.demo.job.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JobProperties implements Serializable {

    @Value("${job.args.jobName}")
    private String jobName;

}
