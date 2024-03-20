package ru.otus.spring.homework6.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework6.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> findById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "left join fetch b.comments " +
                "left join fetch b.author " +
                "left join fetch b.genre where b.id = :id", Book.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "left join fetch b.comments " +
                "left join fetch b.author " +
                "left join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
        }
        return em.merge(book);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
