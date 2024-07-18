package ru.otus.spring.libraryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.libraryservice.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
