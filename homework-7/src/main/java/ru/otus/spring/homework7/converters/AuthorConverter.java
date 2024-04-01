package ru.otus.spring.homework7.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.dto.AuthorDto;
import ru.otus.spring.homework7.models.Author;

@Component
public class AuthorConverter {

    public String authorDtoToString(AuthorDto authorDto) {
        return "Id: %d, FullName: %s".formatted(authorDto.getId(), authorDto.getFullName());
    }

    public AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getFullName()
        );
    }

}
