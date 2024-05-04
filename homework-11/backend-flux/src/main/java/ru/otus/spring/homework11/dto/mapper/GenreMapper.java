package ru.otus.spring.homework11.dto.mapper;

import ru.otus.spring.homework11.dto.GenreDto;
import ru.otus.spring.homework11.models.Genre;

public interface GenreMapper {

    GenreDto toDTO(Genre genre);
    Genre toDomain(GenreDto genreDto);
}
