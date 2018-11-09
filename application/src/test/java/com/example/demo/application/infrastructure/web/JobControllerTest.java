package com.example.demo.application.infrastructure.web;

import com.example.demo.application.usecase.TriggerWordCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(JobController.class)
class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TriggerWordCount triggerWordCount;

    @Test
    void wordCount_shouldTriggerWordCount() throws Exception {

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/jobs/wordCount"));

        // Then
        resultActions.andExpect(status().isOk());
        then(triggerWordCount).should().trigger();
    }
}