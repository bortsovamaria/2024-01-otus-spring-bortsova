package ru.otus.spring.homework10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework10.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
