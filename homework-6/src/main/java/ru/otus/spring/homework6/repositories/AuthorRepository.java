package ru.otus.spring.homework6.repositories;

import ru.otus.spring.homework6.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
