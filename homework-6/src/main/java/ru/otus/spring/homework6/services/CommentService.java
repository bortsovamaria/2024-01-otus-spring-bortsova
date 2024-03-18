package ru.otus.spring.homework6.services;

import ru.otus.spring.homework6.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);

    List<Comment> findByBookId(long bookId);

    void saveComment(long bookId, String text);
}
