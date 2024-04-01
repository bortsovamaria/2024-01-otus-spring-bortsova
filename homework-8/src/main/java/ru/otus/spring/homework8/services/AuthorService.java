package ru.otus.spring.homework8.services;


import ru.otus.spring.homework8.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
