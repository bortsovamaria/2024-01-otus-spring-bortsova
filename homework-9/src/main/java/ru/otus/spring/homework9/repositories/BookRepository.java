package ru.otus.spring.homework9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework9.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
