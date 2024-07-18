package ru.otus.spring.libraryservice.services;


import ru.otus.spring.libraryservice.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
