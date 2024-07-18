package ru.otus.spring.libraryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.libraryservice.models.Book;
import ru.otus.spring.libraryservice.services.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/api/books")
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

}
