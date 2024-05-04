package ru.otus.spring.homework10.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework10.models.Genre;

import java.util.List;

@UtilityClass
public class GenreUtils {

    public static List<Genre> getExpectedGenres() {
        return List.of(
                new Genre(1, "genre 1"),
                new Genre(2, "genre 2"),
                new Genre(3, "genre 3")
        );
    }
}
