package ru.otus.spring.homework10.services;


import ru.otus.spring.homework10.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
