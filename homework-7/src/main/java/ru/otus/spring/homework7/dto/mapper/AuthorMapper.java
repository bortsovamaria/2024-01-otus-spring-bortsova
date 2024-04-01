package ru.otus.spring.homework7.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.homework7.dto.AuthorDto;
import ru.otus.spring.homework7.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDTO(Author author);
}
