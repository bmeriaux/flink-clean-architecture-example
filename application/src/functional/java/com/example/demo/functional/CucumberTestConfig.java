package com.example.demo.functional;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = CucumberContextInitializer.class)
public abstract class CucumberTestConfig {

}
