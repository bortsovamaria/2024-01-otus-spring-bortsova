package ru.otus.spring.homework8.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework8.models.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
