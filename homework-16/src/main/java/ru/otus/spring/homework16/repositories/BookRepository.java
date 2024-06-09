package ru.otus.spring.homework16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework16.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
