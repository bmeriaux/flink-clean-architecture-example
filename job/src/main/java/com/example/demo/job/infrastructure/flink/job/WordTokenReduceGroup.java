package com.example.demo.job.infrastructure.flink.job;

import com.example.demo.core.domain.WordToken;
import com.example.demo.core.usecase.AccumulateWordTokenByCount;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

@Component
public class WordTokenReduceGroup implements GroupReduceFunction<WordToken, WordToken> {

    private final AccumulateWordTokenByCount accumulateWordTokenByCount;

    public WordTokenReduceGroup(AccumulateWordTokenByCount accumulateWordTokenByCount) {
        this.accumulateWordTokenByCount = accumulateWordTokenByCount;
    }

    @Override
    public void reduce(Iterable<WordToken> wordTokens, Collector<WordToken> out) {
        WordToken reducedWordToken = StreamSupport.stream(wordTokens.spliterator(), false)
            .reduce(new WordToken(), accumulateWordTokenByCount);
        out.collect(reducedWordToken);
    }
}
