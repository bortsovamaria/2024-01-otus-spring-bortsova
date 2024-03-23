package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);

    List<Comment> findByBookId(long bookId);

    Comment insert(String text, long bookId);

    Comment update(long id, String text, long bookId);

    void deleteById(long id);
}
