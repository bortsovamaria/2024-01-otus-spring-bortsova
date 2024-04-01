package ru.otus.spring.homework8.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework8.models.Genre;

import java.util.List;

@UtilityClass
public class GenreUtils {

    public static final String ALL_GENRES_RESULT = "Id: 1, Name: Genre_1,\r\n" +
            "Id: 2, Name: Genre_2,\r\n" +
            "Id: 3, Name: Genre_3";

    public static List<Genre> getExpectedGenres() {
        return List.of(
                new Genre(1, "genre 1"),
                new Genre(2, "genre 2"),
                new Genre(3, "genre 3")
        );
    }
}
