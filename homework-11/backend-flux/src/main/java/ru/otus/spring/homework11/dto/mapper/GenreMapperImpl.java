package ru.otus.spring.homework11.dto.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework11.dto.GenreDto;
import ru.otus.spring.homework11.models.Genre;

@Component
public class GenreMapperImpl implements GenreMapper {
    @Override
    public GenreDto toDTO(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    @Override
    public Genre toDomain(GenreDto genreDto) {
        return new Genre(genreDto.getId(), genreDto.getName());
    }
}
