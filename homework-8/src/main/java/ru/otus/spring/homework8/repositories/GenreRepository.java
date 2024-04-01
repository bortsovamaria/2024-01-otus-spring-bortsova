package ru.otus.spring.homework8.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework8.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
