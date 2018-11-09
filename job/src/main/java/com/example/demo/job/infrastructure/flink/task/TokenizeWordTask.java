package com.example.demo.job.infrastructure.flink.task;

import com.example.demo.core.domain.WordToken;
import com.example.demo.core.usecase.TokenizeLine;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Component;

@Component
public class TokenizeWordTask implements FlatMapFunction<String, WordToken> {

    private final TokenizeLine wordTokenizer;

    public TokenizeWordTask(TokenizeLine wordTokenizer) {
        this.wordTokenizer = wordTokenizer;
    }

    @Override
    public void flatMap(String value, Collector<WordToken> out) {
        wordTokenizer.execute(value)
            .forEach(out::collect);
    }
}
