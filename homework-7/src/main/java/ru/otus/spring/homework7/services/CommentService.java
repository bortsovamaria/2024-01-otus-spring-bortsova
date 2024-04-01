package ru.otus.spring.homework7.services;

import ru.otus.spring.homework7.dto.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<CommentDto> findById(long id);

    List<CommentDto> findByBookId(long bookId);

    CommentDto insert(String text, long bookId);

    CommentDto update(long id, String text, long bookId);

    void deleteById(long id);
}
