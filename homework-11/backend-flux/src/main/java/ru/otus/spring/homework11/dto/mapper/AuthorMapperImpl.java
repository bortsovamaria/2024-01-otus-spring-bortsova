package ru.otus.spring.homework11.dto.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework11.dto.AuthorDto;
import ru.otus.spring.homework11.models.Author;

@Component
public class AuthorMapperImpl implements AuthorMapper {
    @Override
    public AuthorDto toDTO(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }

    @Override
    public Author toDomain(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getFullName());
    }
}
