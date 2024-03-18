package ru.otus.spring.homework6.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework6.models.Author;
import ru.otus.spring.homework6.models.Book;
import ru.otus.spring.homework6.models.Comment;
import ru.otus.spring.homework6.models.Genre;
import ru.otus.spring.homework6.repositories.BookRepository;
import ru.otus.spring.homework6.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с комментариями ")
@SpringBootTest(classes = CommentServiceImpl.class)
public class CommentServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @MockBean
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @DisplayName("должен корректно находить комментарий по идентификатору")
    @Test
    void shouldCorrectFindCommentById() {
        Comment expectedComment =
                new Comment(1L, "text", new Book(1, "title", new Author(), new Genre()));
        given(commentRepository.findById(eq(1L))).willReturn(Optional.of(expectedComment));
        Optional<Comment> actualBook = commentService.findById(1L);
        actualBook.ifPresent(book -> assertEquals(expectedComment, book));
        verify(commentRepository, times(1)).findById(eq(1L));
    }

    @DisplayName("должен корректно находить комментарии по идентификатору книги")
    @Test
    void shouldCorrectFindCommentByBookId() {
        Comment expectedComment =
                new Comment(1L, "text", new Book(1, "title", new Author(), new Genre()));
        given(commentRepository.findAllByBookId((eq(1L)))).willReturn(List.of(expectedComment));
        List<Comment> actualComments = commentService.findByBookId(1L);
        assertThat(actualComments).contains(expectedComment);
        verify(commentRepository, times(1)).findAllByBookId(eq(1L));
    }

    @DisplayName("должен корректно добавлять комментарий")
    @Test
    void shouldCorrectSaveBook() {
        Author author = new Author(1L, "author 1");
        Genre genre = new Genre(1L, "genre 1");
        Book book = new Book(1L, "title", author, genre);
        Comment comment = new Comment(0L, "text", book);

        doNothing().when(commentRepository).save(eq(comment));
        given(bookRepository.findById(eq(1L))).willReturn(Optional.of(book));
        commentService.saveComment(1L,"text");
        verify(commentRepository, times(1)).save(eq(comment));
    }
}
