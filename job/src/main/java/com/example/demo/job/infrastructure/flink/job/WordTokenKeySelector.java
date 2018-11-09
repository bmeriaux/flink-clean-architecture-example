package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.core.domain.WordToken;
import org.apache.flink.api.java.functions.KeySelector;

public class WordTokenKeySelector implements KeySelector<WordToken, String> {
    @Override
    public String getKey(WordToken wordToken) {
        return wordToken.getWord();
    }
}
