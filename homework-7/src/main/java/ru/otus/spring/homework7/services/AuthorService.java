package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
