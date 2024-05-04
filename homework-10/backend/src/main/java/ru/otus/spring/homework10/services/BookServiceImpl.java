package ru.otus.spring.homework10.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework10.models.Book;
import ru.otus.spring.homework10.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
