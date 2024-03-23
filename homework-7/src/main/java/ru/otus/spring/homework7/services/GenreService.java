package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
