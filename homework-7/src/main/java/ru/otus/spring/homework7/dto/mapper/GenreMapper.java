package ru.otus.spring.homework7.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.homework7.dto.GenreDto;
import ru.otus.spring.homework7.models.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDTO(Genre genre);
}
