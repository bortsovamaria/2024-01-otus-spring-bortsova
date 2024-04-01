package ru.otus.spring.homework7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework7.converters.CommentConverter;
import ru.otus.spring.homework7.dto.BookDto;
import ru.otus.spring.homework7.dto.CommentDto;
import ru.otus.spring.homework7.exceptions.EntityNotFoundException;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.models.Comment;
import ru.otus.spring.homework7.repositories.BookRepository;
import ru.otus.spring.homework7.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private final CommentConverter commentConverter;

    @Override
    public Optional<CommentDto> findById(long id) {
        return commentRepository.findById(id).map(commentConverter::toDto);
    }

    @Override
    public List<CommentDto> findByBookId(long bookId) {
        Optional<BookDto> book = bookService.findById(bookId);
        if (book.isPresent()) {
            return book.get().getCommentsDto();
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Transactional
    @Override
    public CommentDto insert(String note, long bookId) {
        var comment = save(0, note, bookId);
        return commentConverter.toDto(comment);
    }

    @Transactional
    @Override
    public CommentDto update(long id, String note, long bookId) {
        var comment = save(id, note, bookId);
        return commentConverter.toDto(comment);
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
