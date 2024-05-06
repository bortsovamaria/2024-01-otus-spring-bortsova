package ru.otus.spring.homework11.dto.mapper;

import ru.otus.spring.homework11.dto.BookCreateDto;
import ru.otus.spring.homework11.dto.BookDto;
import ru.otus.spring.homework11.dto.BookUpdateDto;
import ru.otus.spring.homework11.models.Author;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Genre;

public interface BookMapper {

    BookDto toDTO(Book book, Author author, Genre genre);

    BookUpdateDto toUpdateDTO(Book book, Author author, Genre genre);

    BookCreateDto toCreateDTO(Book book, Author author, Genre genre);

    Book toDomainUpdateDTO(BookUpdateDto bookUpdateDto);

    Book toDomainCreateDTO(BookCreateDto bookCreateDto);

}
