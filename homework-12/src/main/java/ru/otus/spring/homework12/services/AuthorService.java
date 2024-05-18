package ru.otus.spring.homework12.services;

import ru.otus.spring.homework12.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
