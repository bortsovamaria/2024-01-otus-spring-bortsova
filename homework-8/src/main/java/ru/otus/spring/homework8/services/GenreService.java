package ru.otus.spring.homework8.services;


import ru.otus.spring.homework8.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
