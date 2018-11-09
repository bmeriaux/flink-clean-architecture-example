package com.example.demo.job.infrastructure.flink.task;

import com.example.demo.core.domain.WordToken;
import com.example.demo.core.usecase.TokenizeLine;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TokenizeWordTaskTest {

    @InjectMocks
    private TokenizeWordTask tokenizeWordTask;

    @Mock
    private TokenizeLine tokenizeLine;

    @Test
    void flatMap_shouldReturn() {
        // Given
        String line = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut";

        List<WordToken> wordtokens = Arrays.asList(
            WordToken.builder().word("lorem").count(1).build(),
            WordToken.builder().word("ipsum").count(1).build(),
            WordToken.builder().word("dolor").count(1).build(),
            WordToken.builder().word("sit").count(1).build(),
            WordToken.builder().word("amet").count(1).build(),
            WordToken.builder().word("consectetur").count(1).build(),
            WordToken.builder().word("adipiscing").count(1).build(),
            WordToken.builder().word("elit").count(1).build(),
            WordToken.builder().word("sed").count(1).build(),
            WordToken.builder().word("do").count(1).build(),
            WordToken.builder().word("eiusmod").count(1).build(),
            WordToken.builder().word("tempor").count(1).build(),
            WordToken.builder().word("incididunt").count(1).build(),
            WordToken.builder().word("ut").count(1).build()
        );

        List<WordToken> outTokens = new ArrayList<>();
        Collector<WordToken> collector = new ListCollector<>(outTokens);

        given(tokenizeLine.execute(line)).willReturn(wordtokens);
        // When
        tokenizeWordTask.flatMap(line, collector);

        // Then
        assertThat(outTokens).containsExactlyElementsOf(wordtokens);
    }

}