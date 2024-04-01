package ru.otus.spring.homework7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework7.dto.BookDto;
import ru.otus.spring.homework7.dto.BookPartDto;
import ru.otus.spring.homework7.dto.mapper.BookMapper;
import ru.otus.spring.homework7.exceptions.EntityNotFoundException;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.repositories.AuthorRepository;
import ru.otus.spring.homework7.repositories.BookRepository;
import ru.otus.spring.homework7.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<BookDto> findById(long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookPartDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toPartDTO)
                .toList();
    }

    @Transactional
    @Override
    public BookDto insert(String title, long authorId, long genreId) {
        var book = save(0, title, authorId, genreId);
        return bookMapper.toDTO(book);
    }

    @Transactional
    @Override
    public BookDto update(long id, String title, long authorId, long genreId) {
        var book = save(id, title, authorId, genreId);
        return bookMapper.toDTO(book);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Book save(long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = new Book(id, title, author, genre);
        return bookRepository.save(book);
    }
}
