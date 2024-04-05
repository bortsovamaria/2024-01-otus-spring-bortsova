package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();
}
