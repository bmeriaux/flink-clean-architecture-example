package com.example.demo.functional.steps;

import com.example.demo.core.domain.WordToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Locale.FRANCE;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    private static final List<Class> TYPE_LIST = asList(
        WordToken.class
    );

    @Override
    public Locale locale() {
        return FRANCE;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {

        ObjectMapper objectMapper = buildObjectMapper();

        TYPE_LIST.forEach(aClass -> typeRegistry.defineDataTableType(
            new DataTableType(
                aClass,
                (Map<String, String> row) -> objectMapper.convertValue(row, aClass))
        ));
    }

    private ObjectMapper buildObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(javaTimeModule);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return objectMapper;
    }
}
