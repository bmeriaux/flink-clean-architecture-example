package com.example.demo.job;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


class MainTest {

    @Test
    void main_shouldThrowException_whenJobNameIsNotPassedByCli() {
        // When
        Throwable throwable = catchThrowable(() -> Main.main(new String[0]));

        // Then
        assertThat(throwable)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("invalid job Name");
    }
}