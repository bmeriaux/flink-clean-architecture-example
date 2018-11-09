package com.example.demo.application.infrastructure.web;

import com.example.demo.application.usecase.TriggerWordCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    private final TriggerWordCount triggerWordCount;

    public JobController(TriggerWordCount triggerWordCount) {
        this.triggerWordCount = triggerWordCount;
    }

    @PostMapping("/wordCount")
    public ResponseEntity wordCount() {
        try {
            triggerWordCount.trigger();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("error while triggering wordCount", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
