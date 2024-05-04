package ru.otus.spring.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework10.dto.BookDto;
import ru.otus.spring.homework10.dto.mapper.BookMapper;
import ru.otus.spring.homework10.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    private final BookMapper bookMapper;

    @GetMapping("/api/books")
    public List<BookDto> findAllBooks() {
        return bookService.findAll().stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/api/books")
    public BookDto insertBook(@RequestBody BookDto bookDto) {
        return bookMapper.toDTO(bookService.save(bookMapper.toDomain(bookDto)));
    }

    @PutMapping("/api/books")
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return bookMapper.toDTO(bookService.save(bookMapper.toDomain(bookDto)));

    }

    @DeleteMapping("/api/books/{id}")
    public void deleteMethod(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }
}