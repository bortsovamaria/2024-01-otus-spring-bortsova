package ru.otus.spring.homework11.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.spring.homework11.models.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {
}
