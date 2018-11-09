package com.example.demo.core.usecase;

import com.example.demo.core.domain.WordToken;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenizeLine implements Serializable {

    public List<WordToken> execute(String line) {
        return Arrays.stream(line.toLowerCase().split("\\W+"))
            .filter(word -> word.length() > 0)
            .map(word -> WordToken.builder().word(word).count(1).build())
            .collect(Collectors.toList());
    }
}
