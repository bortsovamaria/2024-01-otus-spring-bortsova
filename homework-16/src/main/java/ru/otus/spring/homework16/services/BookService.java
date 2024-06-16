package ru.otus.spring.homework16.services;

import ru.otus.spring.homework16.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(Book book);

    Book update(Book book);

    void delete(Book book);
}
