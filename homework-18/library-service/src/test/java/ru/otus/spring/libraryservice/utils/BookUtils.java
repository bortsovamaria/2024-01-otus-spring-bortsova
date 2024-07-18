package ru.otus.spring.libraryservice.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.libraryservice.models.Author;
import ru.otus.spring.libraryservice.models.Book;
import ru.otus.spring.libraryservice.models.Genre;

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
