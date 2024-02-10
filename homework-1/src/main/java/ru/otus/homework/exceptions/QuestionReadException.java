package ru.otus.homework.exceptions;

public class QuestionReadException extends RuntimeException {

    public QuestionReadException(String message, Throwable ex) {
        super(message, ex);
    }
}
