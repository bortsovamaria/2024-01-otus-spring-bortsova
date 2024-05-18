package ru.otus.spring.homework12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework12.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
