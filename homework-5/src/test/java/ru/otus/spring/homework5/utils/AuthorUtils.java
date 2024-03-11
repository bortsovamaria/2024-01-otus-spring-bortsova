package ru.otus.spring.homework5.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework5.models.Author;

import java.util.List;

@UtilityClass
public class AuthorUtils {

    public static final String ALL_AUTHORS_RESULT = "Id: 1, FullName: Author_1,\r\n" +
            "Id: 2, FullName: Author_2,\r\n" +
            "Id: 3, FullName: Author_3";

    public static List<Author> getExpectedAuthors() {
        return List.of(
                new Author(1, "author 1"),
                new Author(2, "author 2"),
                new Author(3, "author 3")
        );
    }
}
