package ru.otus.spring.homework13.services;

import ru.otus.spring.homework13.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
