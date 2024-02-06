package ru.otus.homework.service;

import ru.otus.homework.domain.Question;

import java.io.Reader;
import java.util.List;

public interface QuestionStreamConvertService {

    List<Question> convertStreamToDomainObject(Reader reader);
}
