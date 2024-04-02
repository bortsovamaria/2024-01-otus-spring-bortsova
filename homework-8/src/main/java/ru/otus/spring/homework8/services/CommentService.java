package ru.otus.spring.homework8.services;

import ru.otus.spring.homework8.dto.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<CommentDto> findById(String id);

    List<CommentDto> findByBookId(String bookId);

    CommentDto insert(String text, String bookId);

    CommentDto update(String id, String text, String bookId);

    void deleteById(String id);
}
