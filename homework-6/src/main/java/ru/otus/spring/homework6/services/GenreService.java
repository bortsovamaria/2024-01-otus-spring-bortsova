package ru.otus.spring.homework6.services;

import ru.otus.spring.homework6.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
