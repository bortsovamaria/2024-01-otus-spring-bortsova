package ru.otus.spring.homework8.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework8.dto.BookDto;
import ru.otus.spring.homework8.dto.mapper.BookMapper;
import ru.otus.spring.homework8.dto.mapper.BookMapperImpl;
import ru.otus.spring.homework8.models.Author;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.models.Genre;
import ru.otus.spring.homework8.repositories.AuthorRepository;
import ru.otus.spring.homework8.repositories.BookRepository;
import ru.otus.spring.homework8.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.homework8.utils.BookUtils.getExpectedBooks;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с книгами ")
@SpringBootTest(classes = {BookServiceImpl.class, BookMapperImpl.class})
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
    BookMapper bookMapper;

    @DisplayName("должен корректно находить книгу по идентификатору")
    @Test
    void shouldCorrectFindBookById() {
        Book expectedBook = new Book("1", "title", new Author(), new Genre());
        given(bookRepository.findById(eq("1"))).willReturn(Optional.of(expectedBook));
        Optional<BookDto> actualBook = bookService.findById("1");
        actualBook.ifPresent(book -> assertEquals(bookMapper.toDTO(expectedBook), book));
        verify(bookRepository, times(1)).findById(eq("1"));
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
        Author author = new Author("1", "author 1");
        Genre genre = new Genre("1", "genre 1");
        Book expectedBook = new Book(null, "title", author, genre);
        given(authorRepository.findById(eq("1"))).willReturn(Optional.of(author));
        given(genreRepository.findById(eq("1"))).willReturn(Optional.of(genre));
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);
        BookDto actualBook = bookService.insert("title", "1", "1");
        assertEquals(bookMapper.toDTO(expectedBook), actualBook);
        verify(bookRepository, times(1)).save(eq(expectedBook));
    }

    @DisplayName("должен корректно обновлять книгу")
    @Test
    void shouldCorrectUpdateBook() {
        Author firstAuthor = new Author("1", "author 1");
        Author secondAuthor = new Author("2", "author 2");
        Genre genre = new Genre("1", "genre 1");
        Book expectedBook = new Book("1", "new title", secondAuthor, genre);
        given(authorRepository.findById(eq("1"))).willReturn(Optional.of(firstAuthor));
        given(authorRepository.findById(eq("2"))).willReturn(Optional.of(secondAuthor));
        given(genreRepository.findById(eq("1"))).willReturn(Optional.of(genre));
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);
        BookDto actualBook = bookService.update("1", "new title", "2", "1");
        assertEquals(bookMapper.toDTO(expectedBook), actualBook);
        verify(bookRepository, times(1)).save(eq(expectedBook));
    }

    @DisplayName("должен корректно удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteById() {
        doNothing().when(bookRepository).deleteById(eq("1"));
        bookService.deleteById("1");
        verify(bookRepository, times(1)).deleteById(eq("1"));
    }
}