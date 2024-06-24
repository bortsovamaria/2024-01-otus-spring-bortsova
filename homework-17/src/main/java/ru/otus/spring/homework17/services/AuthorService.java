package ru.otus.spring.homework17.services;

import ru.otus.spring.homework17.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
