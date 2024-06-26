package ru.otus.spring.homework10.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework10.models.Author;
import ru.otus.spring.homework10.models.Book;
import ru.otus.spring.homework10.models.Genre;

import java.util.List;

@UtilityClass
public class BookUtils {

    public static List<Book> getExpectedBooks() {
        return List.of(
                new Book(1L, "book 1", new Author(), new Genre()),
                new Book(1L, "book 1", new Author(), new Genre()),
                new Book(1L, "book 1", new Author(), new Genre())
        );
    }
}
