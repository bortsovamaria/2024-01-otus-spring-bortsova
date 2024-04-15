package ru.otus.spring.homework9.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.homework9.models.Author;
import ru.otus.spring.homework9.models.Book;
import ru.otus.spring.homework9.models.Genre;
import ru.otus.spring.homework9.services.AuthorService;
import ru.otus.spring.homework9.services.BookService;
import ru.otus.spring.homework9.services.GenreService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        List<Book> books = List.of(new Book(1L, "new book", new Author(), new Genre()));
        given(bookService.findAll()).willReturn(books);

        MvcResult result = mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("new book"));
    }

    @Test
    void shouldCorrectSaveNewBook() throws Exception {
        given(authorService.findAll()).willReturn(List.of(new Author(1L, "author")));
        given(genreService.findAll()).willReturn(List.of(new Genre(1L, "genre")));

        Book book = new Book("saved book",
                new Author(1L, "author"), new Genre(1L, "genre"));
        given(bookService.insert(book))
                .willReturn(book);

        mvc.perform(get("/books/add?id=1"))
                .andExpect(status().isOk())
                .andReturn();

        mvc.perform(post("/books/add?id=1"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void shouldCorrectUpdateBook() throws Exception {
        Book updatedBook = new Book("updated book",
                new Author(1L, "author"), new Genre(1L, "genre"));

        given(bookService.findById(1L)).willReturn(Optional.of(updatedBook));

        given(bookService.update(any()))
                .willReturn(updatedBook);

        mvc.perform(get("/books/edit?id=1"))
                .andExpect(status().isOk())
                .andReturn();

        mvc.perform(post("/books/edit?id=1"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void shouldCorrectDeleteBook() throws Exception {
        Book book = new Book(1L, "title", new Author(1L, "author"), new Genre(1L, "genre"));
        given(bookService.findById(1L)).willReturn(Optional.of(book));

        doNothing().when(bookService).delete(book);
        mvc.perform(post("/books/delete?id=1"))
                .andExpect(status().is3xxRedirection());
    }
}