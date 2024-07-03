package ru.otus.spring.libraryservice.services;


import ru.otus.spring.libraryservice.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
