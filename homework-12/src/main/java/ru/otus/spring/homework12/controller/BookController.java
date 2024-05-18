package ru.otus.spring.homework12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework12.exceptions.EntityNotFoundException;
import ru.otus.spring.homework12.models.Author;
import ru.otus.spring.homework12.models.Book;
import ru.otus.spring.homework12.models.Genre;
import ru.otus.spring.homework12.services.AuthorService;
import ru.otus.spring.homework12.services.BookService;
import ru.otus.spring.homework12.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/books")
    public String findAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/books/add")
    public String addPage(Model model) {
        Book book = new Book();
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books/add";
    }

    @PostMapping("/books/add")
    public String insertBook(@ModelAttribute("book") Book book) {
        bookService.insert(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(EntityNotFoundException::new);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books/edit";
    }

    @PostMapping("/books/edit")
    public String saveBook(Book book) {
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteMethod(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("book", book);
        return "books/delete";
    }

    @PostMapping("/books/delete")
    public String deleteBook(Book book) {
        bookService.delete(book);
        return "redirect:/books";
    }
}
