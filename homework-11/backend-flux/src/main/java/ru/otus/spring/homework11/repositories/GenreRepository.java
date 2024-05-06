package ru.otus.spring.homework11.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import ru.otus.spring.homework11.models.Genre;

public interface GenreRepository extends R2dbcRepository<Genre, Long> {
}
