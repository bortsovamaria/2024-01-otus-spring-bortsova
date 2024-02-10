package ru.otus.homework.service;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.homework.dao.dto.QuestionDto;
import ru.otus.homework.domain.Question;

import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionStreamConvertServiceImpl implements QuestionStreamConvertService {
    @Override
    public List<Question> convertStreamToDomainObject(Reader reader) {
        List<QuestionDto> listDto = new CsvToBeanBuilder<QuestionDto>(reader)
                .withType(QuestionDto.class)
                .withSeparator(';')
                .withSkipLines(1)
                .build()
                .parse();

        return listDto.stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }
}
