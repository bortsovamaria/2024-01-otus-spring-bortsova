package ru.otus.spring.homework6.repositories;

import ru.otus.spring.homework6.models.Comment;

import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> findById(long id);

    Comment save(Comment newComment);

    void deleteById(long id);

}
