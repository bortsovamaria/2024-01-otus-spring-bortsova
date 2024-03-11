package ru.otus.spring.homework5.services;

import ru.otus.spring.homework5.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
