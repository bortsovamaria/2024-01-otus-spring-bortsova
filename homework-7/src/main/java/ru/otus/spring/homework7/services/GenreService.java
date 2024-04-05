package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();
}
