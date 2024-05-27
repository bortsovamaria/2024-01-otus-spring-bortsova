package ru.otus.spring.homework13.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework13.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
