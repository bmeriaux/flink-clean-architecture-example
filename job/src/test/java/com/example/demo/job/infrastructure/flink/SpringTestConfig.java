package com.example.demo.job.infrastructure.flink;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.example.demo.job", "com.example.demo.core"})
@PropertySource("classpath:application.properties")
public class SpringTestConfig {
}
