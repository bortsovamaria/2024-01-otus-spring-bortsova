package ru.otus.spring.homework6.repositories;

import ru.otus.spring.homework6.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
