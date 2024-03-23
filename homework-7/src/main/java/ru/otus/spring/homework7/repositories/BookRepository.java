package ru.otus.spring.homework7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.homework7.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b " +
            "left join fetch b.comments " +
            "left join fetch b.author " +
            "left join fetch b.genre")
    List<Book> findAll();

    @Query("select b from Book b left join fetch b.comments " +
            "left join fetch b.author " +
            "left join fetch b.genre where b.id = :id")
    Optional<Book> findById(@Param("id") Long id);

    @Modifying
    @Query("delete from Book b where b.id = :id")
    void deleteById(@Param("id") long id);
}
