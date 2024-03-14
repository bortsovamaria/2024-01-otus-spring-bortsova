package ru.otus.spring.homework5.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework5.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
