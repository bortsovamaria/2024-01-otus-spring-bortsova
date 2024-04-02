package ru.otus.spring.homework8.services;


import ru.otus.spring.homework8.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();
}
