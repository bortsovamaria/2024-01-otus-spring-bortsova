package ru.otus.spring.homework13.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework13.models.Author;

import java.util.List;

@UtilityClass
public class AuthorUtils {

    public static List<Author> getExpectedAuthors() {
        return List.of(
                new Author(1, "author 1"),
                new Author(2, "author 2"),
                new Author(3, "author 3")
        );
    }
}
