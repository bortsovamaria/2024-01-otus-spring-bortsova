package ru.otus.spring.homework6.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework6.models.Book;
import ru.otus.spring.homework6.models.Comment;
import ru.otus.spring.homework6.repositories.BookRepository;
import ru.otus.spring.homework6.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Transactional
    @Override
    public void saveComment(long bookId, String text) {
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresent(value -> commentRepository.save(new Comment(0, text, value)));
    }
}
