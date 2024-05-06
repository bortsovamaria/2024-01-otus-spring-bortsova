package ru.otus.spring.homework11.dto.mapper;

import ru.otus.spring.homework11.dto.AuthorDto;
import ru.otus.spring.homework11.models.Author;

public interface AuthorMapper {

    AuthorDto toDTO(Author author);

    Author toDomain(AuthorDto authorDto);
}
