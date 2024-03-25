package ru.otus.spring.homework6.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework6.models.Comment;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c " +
                "join c.book b where b.id = :bookId", Comment.class);
        query.setParameter("bookId", id);
        return query.getResultList();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
        }
        return em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}
