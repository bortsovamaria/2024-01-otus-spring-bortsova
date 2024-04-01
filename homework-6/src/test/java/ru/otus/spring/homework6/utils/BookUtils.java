package ru.otus.spring.homework6.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework6.models.Author;
import ru.otus.spring.homework6.models.Book;
import ru.otus.spring.homework6.models.Genre;

import java.util.List;

@UtilityClass
public class BookUtils {

    public static final String ALL_BOOK_RESULT =
            "Id: 1, title: editedBook, author: {Id: 3, FullName: Author_3}, genres: [Id: 2, Name: Genre_2],\r\n" +
                    "Id: 2, title: BookTitle_2, author: {Id: 2, FullName: Author_2}, genres: [Id: 2, Name: Genre_2],\r\n" +
                    "Id: 3, title: BookTitle_3, author: {Id: 3, FullName: Author_3}, genres: [Id: 3, Name: Genre_3],\r\n" +
                    "Id: 4, title: newBook, author: {Id: 1, FullName: Author_1}, genres: [Id: 1, Name: Genre_1]";
    public static final String BOOK_BY_ID_RESULT = "Id: 1, title: editedBook, author: {Id: 3, FullName: Author_3}, genres: [Id: 2, Name: Genre_2]";

    public static final String ADD_NEW_BOOK_RESULT = "Id: 4, title: newBook, author: {Id: 1, FullName: Author_1}, genres: [Id: 1, Name: Genre_1]";
    public static final String EDIT_BOOK_RESULT = "Id: 1, title: editedBook, author: {Id: 3, FullName: Author_3}, genres: [Id: 2, Name: Genre_2]";

    public static List<Book> getExpectedBooks() {
        return List.of(
                new Book(1L, "book 1", new Author(), new Genre()),
                new Book(1L, "book 1", new Author(), new Genre()),
                new Book(1L, "book 1", new Author(), new Genre())
        );
    }
}
