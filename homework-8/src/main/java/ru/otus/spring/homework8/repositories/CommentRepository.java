package ru.otus.spring.homework8.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework8.models.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);
}
