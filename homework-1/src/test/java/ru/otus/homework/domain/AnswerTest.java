package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    void shouldHaveCreateAnswerWithConstructor() {
        Answer answer = new Answer("test", true);
        assertAll("answer",
                () -> assertEquals("test", answer.text()),
                () -> assertTrue(answer.isCorrect()));
    }
}