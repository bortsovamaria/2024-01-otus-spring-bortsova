package ru.otus.spring.homework5.repositories;

import ru.otus.spring.homework5.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
