package ru.otus.spring.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.dto.QuestionDto;
import ru.otus.spring.domain.Question;

import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionStreamConvertServiceImpl implements QuestionStreamConvertService {

    @Override
    public List<Question> convertStreamToDomainObject(Reader reader) {
        return convertCsvToDTO(reader).stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }

    private List<QuestionDto> convertCsvToDTO(Reader reader) {
        return new CsvToBeanBuilder<QuestionDto>(reader)
                .withType(QuestionDto.class)
                .withSeparator(';')
                .withSkipLines(1)
                .build()
                .parse();
    }
}