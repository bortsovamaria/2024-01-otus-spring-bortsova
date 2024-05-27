package ru.otus.spring.homework13.services;


import ru.otus.spring.homework13.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
