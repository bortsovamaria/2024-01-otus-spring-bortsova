package ru.otus.spring.homework10.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.homework10.dto.GenreDto;
import ru.otus.spring.homework10.models.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDTO(Genre genre);
}
