package ru.otus.spring.homework10.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.homework10.dto.AuthorDto;
import ru.otus.spring.homework10.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDTO(Author author);
}
