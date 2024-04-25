package ru.otus.spring.homework10.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework10.dto.mapper.BookMapperImpl;
import ru.otus.spring.homework10.models.Author;
import ru.otus.spring.homework10.models.Book;
import ru.otus.spring.homework10.models.Genre;
import ru.otus.spring.homework10.repositories.BookRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.homework10.utils.BookUtils.getExpectedBooks;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с книгами ")
@SpringBootTest(classes = {BookServiceImpl.class, BookMapperImpl.class})
class BookServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @DisplayName("должен корректно находить книгу по идентификатору")
    @Test
    void shouldCorrectFindBookById() {
        Book expectedBook = new Book(1L, "title", new Author(), new Genre());
        given(bookRepository.findById(eq(1L))).willReturn(Optional.of(expectedBook));
        Optional<Book> actualBook = bookService.findById(1L);
        actualBook.ifPresent(book -> assertEquals(expectedBook, book));
        verify(bookRepository, times(1)).findById(eq(1L));
    }

    @DisplayName("должен корректно находить все книги")
    @Test
    void shouldCorrectFindAllBooks() {
        given(bookRepository.findAll()).willReturn(getExpectedBooks());
        bookService.findAll();
        verify(bookRepository, times(1)).findAll();
    }

    @DisplayName("должен корректно добавлять книгу")
    @Test
    void shouldCorrectSaveBook() {
        Author author = new Author(1L, "author 1");
        Genre genre = new Genre(1L, "genre 1");
        Book expectedBook = new Book(0L, "title", author, genre);
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);
        Book actualBook = bookService.save(expectedBook);
        assertEquals(expectedBook, actualBook);
        verify(bookRepository, times(1)).save(eq(expectedBook));
    }

    @DisplayName("должен корректно обновлять книгу")
    @Test
    void shouldCorrectUpdateBook() {
        Author firstAuthor = new Author(1L, "author 1");
        Author secondAuthor = new Author(2L, "author 2");
        Genre genre = new Genre(1L, "genre 1");
        Book expectedBook = new Book(1L, "new title", secondAuthor, genre);
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);
        Book actualBook = bookService.save(expectedBook);
        assertEquals(expectedBook, actualBook);
        verify(bookRepository, times(1)).save(eq(expectedBook));
    }

    @DisplayName("должен корректно удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteById() {
        doNothing().when(bookRepository).deleteById(eq(1L));
        bookService.deleteById(1L);
        verify(bookRepository, times(1)).deleteById(eq(1L));
    }
}