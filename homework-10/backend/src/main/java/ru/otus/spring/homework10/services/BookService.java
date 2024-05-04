package ru.otus.spring.homework10.services;

import ru.otus.spring.homework10.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(long id);
}
