package ru.otus.homework.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.config.TestFileNameProvider;
import ru.otus.homework.dao.dto.QuestionDto;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        List<QuestionDto> listDto;
        try (InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameProvider.getTestFileName());
             Reader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            listDto = new CsvToBeanBuilder<QuestionDto>(reader)
                    .withType(QuestionDto.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .build()
                    .parse();

        } catch (IOException e) {
            throw new QuestionReadException("Error read file: " + fileNameProvider.getTestFileName(), e);
        }

        return listDto.stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }
}
