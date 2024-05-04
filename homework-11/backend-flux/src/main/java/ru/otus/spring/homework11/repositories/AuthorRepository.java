package ru.otus.spring.homework11.repositories;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.spring.homework11.models.Author;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {
}
