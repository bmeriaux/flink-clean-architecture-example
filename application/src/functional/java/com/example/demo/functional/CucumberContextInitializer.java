package com.example.demo.functional;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.util.List;

public class CucumberContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            FileSystemResource applicationPropertiesResource = new FileSystemResource("config/application.yml");
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            List<PropertySource<?>> yamlApplicationProperties = sourceLoader.load("applicationPropertiesResource", applicationPropertiesResource);
            yamlApplicationProperties.forEach(yamlTestPropertie -> applicationContext.getEnvironment().getPropertySources().addAfter("systemEnvironment", yamlTestPropertie));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
