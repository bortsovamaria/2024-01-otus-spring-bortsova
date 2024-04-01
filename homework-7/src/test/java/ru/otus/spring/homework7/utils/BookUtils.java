package ru.otus.spring.homework7.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.homework7.models.Author;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.models.Genre;

import java.util.List;

@UtilityClass
public class BookUtils {

    public static final String ALL_BOOK_RESULT =
            "Id: 1, title: BookTitle_1, author: {AuthorDto(id=1, fullName=Author_1)}, genres: [GenreDto(id=1, name=Genre_1)],\r\n" +
                    "Id: 2, title: BookTitle_2, author: {AuthorDto(id=2, fullName=Author_2)}, genres: [GenreDto(id=2, name=Genre_2)],\r\n" +
                    "Id: 3, title: editedBook, author: {AuthorDto(id=3, fullName=Author_3)}, genres: [GenreDto(id=2, name=Genre_2)],\r\n" +
                    "Id: 4, title: newBook, author: {AuthorDto(id=1, fullName=Author_1)}, genres: [GenreDto(id=1, name=Genre_1)]";
    public static final String BOOK_BY_ID_RESULT = "Id: 2, title: BookTitle_2, author: {AuthorDto(id=2, fullName=Author_2)}, genres: [GenreDto(id=2, name=Genre_2)]";

    public static final String ADD_NEW_BOOK_RESULT = "Id: 4, title: newBook, author: {AuthorDto(id=1, fullName=Author_1)}, genres: [GenreDto(id=1, name=Genre_1)]";
    public static final String EDIT_BOOK_RESULT = "Id: 3, title: editedBook, author: {AuthorDto(id=3, fullName=Author_3)}, genres: [GenreDto(id=2, name=Genre_2)]";

    public static List<Book> getExpectedBooks() {
        return List.of(
                new Book(1L, "book 1", new Author(), new Genre()),
                new Book(1L, "book 1", new Author(), new Genre()),
                new Book(1L, "book 1", new Author(), new Genre())
        );
    }
}
