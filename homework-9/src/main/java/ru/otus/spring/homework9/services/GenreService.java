package ru.otus.spring.homework9.services;

import ru.otus.spring.homework9.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
