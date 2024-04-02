package ru.otus.spring.homework8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework8.dto.CommentDto;
import ru.otus.spring.homework8.dto.mapper.CommentMapper;
import ru.otus.spring.homework8.exceptions.EntityNotFoundException;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.models.Comment;
import ru.otus.spring.homework8.repositories.BookRepository;
import ru.otus.spring.homework8.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private final CommentMapper commentMapper;

    @Override
    public Optional<CommentDto> findById(String id) {
        return commentRepository.findById(id).map(commentMapper::toDTO);
    }

    @Override
    public List<CommentDto> findByBookId(String bookId) {
        return commentRepository.findAllByBookId(bookId).stream().map(commentMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommentDto insert(String note, String bookId) {
        var comment = save("0", note, bookId);
        return commentMapper.toDTO(comment);
    }

    @Transactional
    @Override
    public CommentDto update(String id, String note, String bookId) {
        var comment = save(id, note, bookId);
        return commentMapper.toDTO(comment);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    public Comment save(String id, String text, String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with %s not found".formatted(bookId)));
        Comment comment = new Comment(id, text, book);
        return commentRepository.save(comment);
    }
}