package ru.otus.spring.homework17.services;


import ru.otus.spring.homework17.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
