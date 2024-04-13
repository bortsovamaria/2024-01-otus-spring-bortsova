package ru.otus.spring.homework8.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework8.dto.AuthorDto;

@Component
public class AuthorConverter {
    public String authorDtoToString(AuthorDto authorDto) {
        return "Id: %s, FullName: %s".formatted(authorDto.getId(), authorDto.getFullName());
    }
}
