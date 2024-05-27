package ru.otus.spring.homework14.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework14.models.nosql.GenreDocument;

@Component
public class GenreConverter {

    public String mongoGenreToString(GenreDocument genre) {
        return "Id: %s, Name: %s".formatted(
                genre.getId(),
                genre.getName());
    }
}