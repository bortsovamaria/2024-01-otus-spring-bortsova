package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.io.Reader;
import java.util.List;

public interface QuestionStreamConvertService {

    List<Question> convertStreamToDomainObject(Reader reader);
}