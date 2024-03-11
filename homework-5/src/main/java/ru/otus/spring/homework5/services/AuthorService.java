package ru.otus.spring.homework5.services;

import ru.otus.spring.homework5.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
