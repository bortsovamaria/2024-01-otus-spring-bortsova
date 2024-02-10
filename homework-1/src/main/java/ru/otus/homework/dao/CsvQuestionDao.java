package ru.otus.homework.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.config.TestFileNameProvider;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionReadException;
import ru.otus.homework.service.QuestionStreamConvertService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final QuestionStreamConvertService questionStreamConvertService;

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        try (InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameProvider.getTestFileName());
             Reader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            return questionStreamConvertService.convertStreamToDomainObject(reader);

        } catch (IOException e) {
            throw new QuestionReadException("Error read file: " + fileNameProvider.getTestFileName(), e);
        }
    }
}
