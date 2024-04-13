package ru.otus.spring.homework8.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework8.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
