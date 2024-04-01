package ru.otus.spring.homework7.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework7.converters.AuthorConverter;
import ru.otus.spring.homework7.converters.BookConverter;
import ru.otus.spring.homework7.converters.CommentConverter;
import ru.otus.spring.homework7.converters.GenreConverter;
import ru.otus.spring.homework7.dto.BookDto;
import ru.otus.spring.homework7.models.Author;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.models.Genre;
import ru.otus.spring.homework7.repositories.AuthorRepository;
import ru.otus.spring.homework7.repositories.BookRepository;
import ru.otus.spring.homework7.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.homework7.utils.BookUtils.getExpectedBooks;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с книгами ")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    GenreRepository genreRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BookConverter bookConverter;

    @Autowired
    AuthorConverter authorConverter;

    @Autowired
    GenreConverter genreConverter;

    @Autowired
    CommentConverter commentConverter;

    @BeforeEach
    void setUp() {
        authorConverter = new AuthorConverter();
        genreConverter = new GenreConverter();
        commentConverter = new CommentConverter();
        bookConverter = new BookConverter(authorConverter, genreConverter, commentConverter);
    }

    @DisplayName("должен корректно находить книгу по идентификатору")
    @Test
    void shouldCorrectFindBookById() {
        Book expectedBook = new Book(1L, "title", new Author(), new Genre());
        given(bookRepository.findById(eq(1L))).willReturn(Optional.of(expectedBook));
        Optional<BookDto> actualBook = bookService.findById(1L);
        actualBook.ifPresent(book -> assertEquals(bookConverter.toDto(expectedBook), book));
        verify(bookRepository, times(1)).findById(eq(1L));
    }

    @DisplayName("должен корректно находить все книги")
    @Test
    void shouldCorrectFindAllBooks() {
        given(bookRepository.findAll()).willReturn(getExpectedBooks());
        List<BookDto> actualBooks = bookService.findAll();
        assertEquals(actualBooks, getExpectedBooks().stream().map(bookConverter::toDto).toList());
        verify(bookRepository, times(1)).findAll();
    }

    @DisplayName("должен корректно добавлять книгу")
    @Test
    void shouldCorrectSaveBook() {
        Author author = new Author(1L, "author 1");
        Genre genre = new Genre(1L, "genre 1");
        Book expectedBook = new Book(0L, "title", author, genre);
        given(authorRepository.findById(eq(1L))).willReturn(Optional.of(author));
        given(genreRepository.findById(eq(1L))).willReturn(Optional.of(genre));
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);
        BookDto actualBook = bookService.insert("title", 1L, 1L);
        assertEquals(bookConverter.toDto(expectedBook), actualBook);
        verify(bookRepository, times(1)).save(eq(expectedBook));
    }

    @DisplayName("должен корректно обновлять книгу")
    @Test
    void shouldCorrectUpdateBook() {
        Author firstAuthor = new Author(1L, "author 1");
        Author secondAuthor = new Author(2L, "author 2");
        Genre genre = new Genre(1L, "genre 1");
        Book expectedBook = new Book(1L, "new title", secondAuthor, genre);
        given(authorRepository.findById(eq(1L))).willReturn(Optional.of(firstAuthor));
        given(authorRepository.findById(eq(2L))).willReturn(Optional.of(secondAuthor));
        given(genreRepository.findById(eq(1L))).willReturn(Optional.of(genre));
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);
        BookDto actualBook = bookService.update(1L, "new title", 2L, 1L);
        assertEquals(bookConverter.toDto(expectedBook), actualBook);
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