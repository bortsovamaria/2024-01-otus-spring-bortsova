package ru.otus.spring.homework16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework16.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
