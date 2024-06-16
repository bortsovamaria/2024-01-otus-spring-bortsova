package ru.otus.spring.homework16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework16.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
