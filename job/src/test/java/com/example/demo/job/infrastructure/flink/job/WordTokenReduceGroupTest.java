package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.core.domain.WordToken;
import com.example.demo.core.usecase.AccumulateWordTokenByCount;
import org.apache.flink.api.common.functions.util.ListCollector;
import org.apache.flink.util.Collector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WordTokenReduceGroupTest {

    @InjectMocks
    private WordTokenReduceGroup wordTokenReduceGroup;

    @Mock
    private AccumulateWordTokenByCount accumulateWordTokenByCount;

    @Test
    void reduce_shouldReduceWordTokensCount() {
        // Given
        WordToken wordToken1 = WordToken.builder()
            .word("toto")
            .count(1)
            .build();
        WordToken wordToken2 = WordToken.builder()
            .word("toto")
            .count(2)
            .build();

        List<WordToken> wordTokens = Arrays.asList(wordToken1, wordToken2);
        List<WordToken> reducedWordToken = new ArrayList<>();
        Collector<WordToken> wordTokenCollector = new ListCollector<>(reducedWordToken);
        given(accumulateWordTokenByCount.apply(any(), any())).willCallRealMethod();

        // When
        wordTokenReduceGroup.reduce(wordTokens, wordTokenCollector);

        // Then
        WordToken expectedWordToken = WordToken.builder()
            .word("toto")
            .count(3)
            .build();
        assertThat(reducedWordToken.get(0)).isEqualTo(expectedWordToken);
    }

}