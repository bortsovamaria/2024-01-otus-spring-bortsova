package ru.otus.spring.homework6.services;

import ru.otus.spring.homework6.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
