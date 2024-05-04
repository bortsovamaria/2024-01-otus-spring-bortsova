package ru.otus.spring.homework10.services;

import ru.otus.spring.homework10.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
