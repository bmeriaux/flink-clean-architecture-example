package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.core.domain.WordToken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordTokenKeySelectorTest {

    @Test
    void getKey_shouldReturnWord() {
        // Given
        WordToken wordToken = WordToken.builder()
            .word("coucou")
            .count(1)
            .build();
        WordTokenKeySelector wordTokenKeySelector = new WordTokenKeySelector();

        // When
        String key = wordTokenKeySelector.getKey(wordToken);

        // Then
        assertThat(key).isEqualTo(wordToken.getWord());
    }

}