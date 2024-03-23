package ru.otus.spring.homework7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework7.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
