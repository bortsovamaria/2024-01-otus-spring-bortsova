package ru.otus.spring.homework8.services;


import ru.otus.spring.homework8.dto.BookDto;
import ru.otus.spring.homework8.dto.BookPartDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<BookDto> findById(String id);

    List<BookPartDto> findAll();

    BookDto insert(String title, String authorId, String genreId);

    BookDto update(String id, String title, String authorId, String genreId);

    void deleteById(String id);
}
