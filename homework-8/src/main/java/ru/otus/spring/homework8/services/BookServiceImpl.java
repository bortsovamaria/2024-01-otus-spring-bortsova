package ru.otus.spring.homework8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework8.dto.BookDto;
import ru.otus.spring.homework8.dto.BookPartDto;
import ru.otus.spring.homework8.dto.mapper.BookMapper;
import ru.otus.spring.homework8.exceptions.EntityNotFoundException;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.repositories.AuthorRepository;
import ru.otus.spring.homework8.repositories.BookRepository;
import ru.otus.spring.homework8.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public Optional<BookDto> findById(String id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO);
    }

    @Override
    public List<BookPartDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toPartDTO)
                .toList();
    }

    @Transactional
    @Override
    public BookDto insert(String title, String authorId, String genreId) {
        var book = save(null, title, authorId, genreId);
        return bookMapper.toDTO(book);
    }

    @Transactional
    @Override
    public BookDto update(String id, String title, String authorId, String genreId) {
        var book = save(id, title, authorId, genreId);
        return bookMapper.toDTO(book);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public Book save(String id, String title, String authorId, String genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %s not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %s not found".formatted(genreId)));
        var book = new Book(id, title, author, genre);
        return bookRepository.save(book);
    }
}
