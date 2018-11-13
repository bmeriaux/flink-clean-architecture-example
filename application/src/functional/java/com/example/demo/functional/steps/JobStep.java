package com.example.demo.functional.steps;

import com.example.demo.application.usecase.TriggerWordCount;
import com.example.demo.core.domain.WordToken;
import com.example.demo.functional.run.RunFuncTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class JobStep implements En {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public JobStep(@Value("${jobs.wordCount.filesRootPath}") String fileRootPath) {

        Given("^a file contains the following lines:$", (DataTable dataTable) -> {
            Path fileRoot = Paths.get(fileRootPath);
            FileUtils.deleteDirectory(fileRoot.toFile());
            Files.createDirectories(fileRoot);
            Files.write(Paths.get(fileRootPath, TriggerWordCount.INTPUT_FILE_NAME), dataTable.asList(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        });
        When("^the job (.*) is triggered", (String jobName) -> {
            ResponseEntity responseEntity = restTemplate.postForEntity(getBaseUrl() + "/jobs/" + jobName, null, String.class);
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
        Then("^the word tokens are:$", (DataTable datatable) -> {
            Path outputFile = Paths.get(fileRootPath, TriggerWordCount.OUTPUT_FILE_NAME);
            List<WordToken> wordTokens = Files.readAllLines(outputFile, Charset.defaultCharset()).stream()
                .map(line -> {
                    try {
                        return objectMapper.readValue(line, WordToken.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
            List<WordToken> expectedWordtokens = datatable.asList(WordToken.class);
            assertThat(wordTokens).containsExactlyInAnyOrderElementsOf(expectedWordtokens);
        });
    }

    public String getBaseUrl() {
        return "http://localhost:" + RunFuncTest.serverPort;
    }
}
