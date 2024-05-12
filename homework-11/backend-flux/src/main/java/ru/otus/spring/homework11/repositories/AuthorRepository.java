package ru.otus.spring.homework11.repositories;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import ru.otus.spring.homework11.models.Author;

public interface AuthorRepository extends R2dbcRepository<Author, Long> {
}
