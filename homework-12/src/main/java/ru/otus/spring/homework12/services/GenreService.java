package ru.otus.spring.homework12.services;

import ru.otus.spring.homework12.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
