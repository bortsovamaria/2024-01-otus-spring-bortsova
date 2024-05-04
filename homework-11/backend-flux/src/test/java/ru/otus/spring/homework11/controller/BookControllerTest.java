//package ru.otus.spring.homework11.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.otus.spring.homework11.dto.mapper.BookMapper;
//import ru.otus.spring.homework11.models.Author;
//import ru.otus.spring.homework11.models.Book;
//import ru.otus.spring.homework11.models.Genre;
//import ru.otus.spring.homework11.services.AuthorService;
//import ru.otus.spring.homework11.services.BookService;
//import ru.otus.spring.homework11.services.GenreService;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(BookController.class)
//class BookControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private BookService bookService;
//
//    @MockBean
//    private AuthorService authorService;
//
//    @MockBean
//    private GenreService genreService;
//
//    @MockBean
//    private BookMapper bookMapper;
//
//    @Test
//    void shouldReturnCorrectBooksList() throws Exception {
//        List<Book> books = List.of(new Book(1L, "new book", new Author(), new Genre()));
//        given(bookService.findAll()).willReturn(books);
//
//        mvc.perform(get("/api/books"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    void shouldCorrectSaveNewBook() throws Exception {
//        given(authorService.findAll()).willReturn(List.of(new Author(1L, "author")));
//        given(genreService.findAll()).willReturn(List.of(new Genre(1L, "genre")));
//
//        Book book = new Book("saved book",
//                new Author(1L, "author"), new Genre(1L, "genre"));
//        given(bookService.save(book))
//                .willReturn(book);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = ow.writeValueAsString(book);
//
//        mvc.perform(post("/api/books").contentType(APPLICATION_JSON_VALUE)
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    void shouldCorrectUpdateBook() throws Exception {
//        Book updatedBook = new Book("updated book",
//                new Author(1L, "author"), new Genre(1L, "genre"));
//
//        given(bookService.findById(1L)).willReturn(Optional.of(updatedBook));
//
//        given(bookService.save(any()))
//                .willReturn(updatedBook);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = ow.writeValueAsString(updatedBook);
//
//        mvc.perform(put("/api/books").contentType(APPLICATION_JSON_VALUE)
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    void shouldCorrectDeleteBook() throws Exception {
//        Book book = new Book(1L, "title", new Author(1L, "author"), new Genre(1L, "genre"));
//        given(bookService.findById(1L)).willReturn(Optional.of(book));
//
//        doNothing().when(bookService).deleteById(1L);
//        mvc.perform(delete("/api/books/1"))
//                .andExpect(status().isOk());
//    }
//}