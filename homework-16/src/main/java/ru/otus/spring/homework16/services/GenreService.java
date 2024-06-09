package ru.otus.spring.homework16.services;


import ru.otus.spring.homework16.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
