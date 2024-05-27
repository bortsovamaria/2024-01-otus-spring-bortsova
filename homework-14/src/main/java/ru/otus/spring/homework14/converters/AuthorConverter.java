package ru.otus.spring.homework14.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework14.models.nosql.AuthorDocument;

@Component
public class AuthorConverter {

    public String mongoAuthorToString(AuthorDocument author) {
        return "Id: %s, Full Name: %s".formatted(
                author.getId(),
                author.getFullName());
    }
}