package ru.otus.spring.homework8.services;


import ru.otus.spring.homework8.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();
}
