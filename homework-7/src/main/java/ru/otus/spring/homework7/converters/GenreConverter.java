package ru.otus.spring.homework7.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.dto.GenreDto;
import ru.otus.spring.homework7.models.Genre;

@Component
public class GenreConverter {

    public String genreDtoToString(GenreDto genreDto) {
        return "Id: %d, Name: %s".formatted(genreDto.getId(), genreDto.getName());
    }

    public GenreDto toDto(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName()
        );
    }

}
