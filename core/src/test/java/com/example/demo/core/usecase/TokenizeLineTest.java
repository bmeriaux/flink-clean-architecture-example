package com.example.demo.core.usecase;

import com.example.demo.core.domain.WordToken;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TokenizeLineTest {

    private TokenizeLine tokenizeLine = new TokenizeLine();

    @Test
    void tokenizeLine_shouldReturnAListOfWord() {
        // Given
        String line = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut";

        // When
        List<WordToken> wordTokens = tokenizeLine.execute(line);

        // Then
        List<WordToken> expectedWordtokens = Arrays.asList(
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

        assertThat(wordTokens).containsExactlyElementsOf(expectedWordtokens);
    }
}