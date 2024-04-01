package ru.otus.spring.homework7.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ru.otus.spring.homework7.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();

//    @Override
//    @EntityGraph(attributePaths = {"author", "genre"})
//    Optional<Book> findById(Long id);

}
