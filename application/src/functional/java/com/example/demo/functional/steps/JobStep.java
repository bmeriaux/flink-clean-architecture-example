package com.example.demo.functional.steps;

import com.example.demo.functional.run.RunFuncTest;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;

public class JobStep implements En {

    private RestTemplate restTemplate = new RestTemplate();

    public JobStep(@Value("${jobs.wordCount.filesRootPath}") String fileRootPath) {

        Given("^a file contains the following lines:$", (DataTable dataTable) -> {
            Path fileRoot = Paths.get(fileRootPath);
            FileUtils.deleteDirectory(fileRoot.toFile());
            Files.createDirectories(fileRoot);
            Files.write(Paths.get(fileRootPath, "loremIpsum.txt"), dataTable.asList(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        });
        When("^the job (.*) is triggered", (String jobName) -> {
            ResponseEntity responseEntity = restTemplate.postForEntity(getBaseUrl() + "/jobs/" + jobName, null, String.class);
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
    }

    public String getBaseUrl() {
        return "http://localhost:" + RunFuncTest.serverPort;
    }
}
