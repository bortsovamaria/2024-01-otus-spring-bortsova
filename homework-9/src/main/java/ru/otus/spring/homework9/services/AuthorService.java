package ru.otus.spring.homework9.services;

import ru.otus.spring.homework9.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
