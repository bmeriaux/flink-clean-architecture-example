package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.core.domain.WordToken;
import com.example.demo.job.infrastructure.JobProperties;
import com.example.demo.job.infrastructure.flink.SpringTestConfig;
import com.google.common.io.Files;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(classes = SpringTestConfig.class)
class WordCountJobIntTest {

    @Autowired
    private WordCountJob wordCountJob;

    @Autowired
    private JobProperties jobProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void start_should() throws Exception {
        // When
        wordCountJob.start();

        // Then
        File outputFile = Paths.get(jobProperties.getOutputFilePath()).toFile();
        List<WordToken> wordTokens = Files.readLines(outputFile, Charset.defaultCharset()).stream()
            .map(line -> {
                try {
                    return objectMapper.readValue(line, WordToken.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());
        WordToken wordToken1 = WordToken.builder().word("sit").count(2).build();
        WordToken wordToken2 = WordToken.builder().word("amet").count(2).build();
        WordToken wordToken3 = WordToken.builder().word("ipsum").count(2).build();
        WordToken wordToken4 = WordToken.builder().word("consectetur").count(2).build();
        WordToken wordToken5 = WordToken.builder().word("ut").count(1).build();
        WordToken wordToken6 = WordToken.builder().word("labore").count(1).build();
        WordToken wordToken7 = WordToken.builder().word("elit").count(2).build();
        WordToken wordToken8 = WordToken.builder().word("lorem").count(2).build();
        assertThat(wordTokens).containsExactlyInAnyOrder(wordToken1,
            wordToken2,
            wordToken3,
            wordToken4,
            wordToken5,
            wordToken6,
            wordToken7,
            wordToken8);
    }

}