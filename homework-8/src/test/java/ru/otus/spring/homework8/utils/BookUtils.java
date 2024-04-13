package ru.otus.spring.homework8.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework8.models.Author;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.models.Genre;

import java.util.List;

@UtilityClass
public class BookUtils {

    public static List<Book> getExpectedBooks() {
        return List.of(
                new Book("1", "book 1", new Author(), new Genre()),
                new Book("2", "book 1", new Author(), new Genre()),
                new Book("3", "book 1", new Author(), new Genre())
        );
    }
}
