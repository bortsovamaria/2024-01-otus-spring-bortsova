package ru.otus.spring.libraryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.libraryservice.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
