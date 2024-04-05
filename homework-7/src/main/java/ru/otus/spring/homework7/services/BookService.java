package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.dto.BookDto;
import ru.otus.spring.homework7.dto.BookPartDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<BookDto> findById(long id);

    List<BookPartDto> findAll();

    BookDto insert(String title, long authorId, long genreId);

    BookDto update(long id, String title, long authorId, long genreId);

    void deleteById(long id);
}
