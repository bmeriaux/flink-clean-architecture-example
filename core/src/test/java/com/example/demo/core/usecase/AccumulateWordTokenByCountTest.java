package com.example.demo.core.usecase;

import com.example.demo.core.domain.WordToken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccumulateWordTokenByCountTest {

    private AccumulateWordTokenByCount accumulateWordTokenByCount = new AccumulateWordTokenByCount();

    @Test
    void apply_shouldCombineWordTokenCount() {
        // Given
        WordToken wordToken1 = WordToken.builder()
            .word("toto")
            .count(1)
            .build();

        WordToken wordTokenAcc = WordToken.builder()
            .word("toto")
            .count(3)
            .build();

        // When
        accumulateWordTokenByCount.apply(wordTokenAcc, wordToken1);

        //Then
        WordToken expectedWorkToken = WordToken.builder()
            .word("toto")
            .count(4)
            .build();
        assertThat(wordTokenAcc).isEqualToComparingFieldByField(expectedWorkToken);
    }

}