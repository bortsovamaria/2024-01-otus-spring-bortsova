package ru.otus.spring.libraryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.libraryservice.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
