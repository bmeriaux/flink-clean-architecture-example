package com.example.demo.core.usecase;

import com.example.demo.core.domain.WordToken;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.function.BinaryOperator;

@Component
public class AccumulateWordTokenByCount implements BinaryOperator<WordToken>, Serializable {

    @Override
    public WordToken apply(WordToken wordTokenAcc, WordToken wordToken) {
        wordTokenAcc.setWord(wordToken.getWord());
        wordTokenAcc.setCount(wordTokenAcc.getCount() + wordToken.getCount());
        return wordTokenAcc;
    }
}
