package com.example.demo.application.usecase;

import com.example.demo.application.domain.JobSubmitter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TriggerWordCountTest {

    TriggerWordCount triggerWordCount;

    @Mock
    JobSubmitter jobSubmitter;

    @Test
    void trigger_shouldSubmitJobWithArgs() throws Exception {
        // Given
        triggerWordCount = new TriggerWordCount(jobSubmitter, "input");
        // When
        triggerWordCount.trigger();

        // Then
        Map<String, String> jobArgs = new HashMap<>();
        jobArgs.put("inputFilePath", "input/loremIpsum.txt");
        then(jobSubmitter).should().submitJob("wordCount", jobArgs);
    }
}