package ru.otus.spring.homework6.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework6.exceptions.EntityNotFoundException;
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

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByBookId(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get().getComments();
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Transactional
    @Override
    public Comment insert(String text, long bookId) {
        return save(0, text, bookId);
    }

    @Override
    public Comment update(long id, String text, long bookId) {
        return save(id, text, bookId);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    public Comment save(long id, String text, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with %d not found".formatted(bookId)));
        Comment comment = new Comment(id, text, book);
        return commentRepository.save(comment);
    }
}
