package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.core.domain.WordToken;
import org.apache.flink.api.java.io.TextOutputFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class WordTokenTextFormatter implements TextOutputFormat.TextFormatter<WordToken> {

    private final ObjectMapper objectMapper;

    public WordTokenTextFormatter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String format(WordToken value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
