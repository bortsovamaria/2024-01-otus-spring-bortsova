package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.config.TestFileNameProvider;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionReadException;
import ru.otus.spring.service.QuestionStreamConvertService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

@Component
public class CsvQuestionDao implements QuestionDao {

    private final QuestionStreamConvertService questionStreamConvertService;

    private final TestFileNameProvider fileNameProvider;

    public CsvQuestionDao(QuestionStreamConvertService questionStreamConvertService, TestFileNameProvider fileNameProvider) {
        this.questionStreamConvertService = questionStreamConvertService;
        this.fileNameProvider = fileNameProvider;
    }

    @Override
    public List<Question> getQuestions() {
        try (InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameProvider.getTestFileName());
             Reader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            return questionStreamConvertService.convertStreamToDomainObject(reader);
        } catch (IOException e) {
            throw new QuestionReadException("Error read file: " + fileNameProvider.getTestFileName(), e);
        }
    }
}