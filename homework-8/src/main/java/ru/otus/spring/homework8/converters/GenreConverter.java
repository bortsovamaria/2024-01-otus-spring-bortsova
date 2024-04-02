package ru.otus.spring.homework8.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework8.dto.GenreDto;

@Component
public class GenreConverter {
    public String genreDtoToString(GenreDto genreDto) {
        return "Id: %s, Name: %s".formatted(genreDto.getId(), genreDto.getName());
    }
}
