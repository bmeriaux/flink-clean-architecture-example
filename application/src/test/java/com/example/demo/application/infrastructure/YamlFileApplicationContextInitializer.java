package com.example.demo.application.infrastructure;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public class YamlFileApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Resource resource = applicationContext.getResource("classpath:application.yml");
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            List<PropertySource<?>> yamlTestProperties = sourceLoader.load("yamlTestProperties", resource);
            yamlTestProperties.forEach(yamlTestPropertie -> applicationContext.getEnvironment().getPropertySources().addFirst(yamlTestPropertie));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}