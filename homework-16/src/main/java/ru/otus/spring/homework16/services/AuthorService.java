package ru.otus.spring.homework16.services;

import ru.otus.spring.homework16.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
