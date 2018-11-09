package com.example.demo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.demo")
public class FlinkCleanArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlinkCleanArchitectureApplication.class, args);
    }
}
