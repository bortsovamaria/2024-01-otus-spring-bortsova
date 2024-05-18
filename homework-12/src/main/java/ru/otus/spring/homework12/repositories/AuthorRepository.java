package ru.otus.spring.homework12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework12.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
