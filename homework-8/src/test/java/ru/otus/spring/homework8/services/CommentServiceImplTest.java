package ru.otus.spring.homework8.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework8.dto.CommentDto;
import ru.otus.spring.homework8.dto.mapper.BookMapper;
import ru.otus.spring.homework8.dto.mapper.BookMapperImpl;
import ru.otus.spring.homework8.dto.mapper.CommentMapper;
import ru.otus.spring.homework8.dto.mapper.CommentMapperImpl;
import ru.otus.spring.homework8.models.Author;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.models.Comment;
import ru.otus.spring.homework8.models.Genre;
import ru.otus.spring.homework8.repositories.AuthorRepository;
import ru.otus.spring.homework8.repositories.BookRepository;
import ru.otus.spring.homework8.repositories.CommentRepository;
import ru.otus.spring.homework8.repositories.GenreRepository;

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
@SpringBootTest(classes = {CommentServiceImpl.class, CommentMapperImpl.class, BookServiceImpl.class, BookMapperImpl.class})
public class CommentServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @MockBean
    CommentRepository commentRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    GenreRepository genreRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    BookService bookService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BookMapper bookMapper;

    @DisplayName("должен корректно находить комментарий по идентификатору")
    @Test
    void shouldCorrectFindCommentById() {
        Comment expectedComment =
                new Comment("1", "text", new Book("1", "title", new Author(), new Genre()));
        given(commentRepository.findById(eq("1"))).willReturn(Optional.of(expectedComment));
        Optional<CommentDto> actualComment = commentService.findById("1");
        actualComment.ifPresent(comment -> assertEquals(commentMapper.toDTO(expectedComment), comment));
        verify(commentRepository, times(1)).findById(eq("1"));
    }

    @DisplayName("должен корректно обновлять комментарий")
    @Test
    void shouldCorrectUpdateComment() {
        Author author = new Author("1", "author 1");
        Genre genre = new Genre("1", "genre 1");
        Book book = new Book("1", "new title", author, genre);
        Comment expectedComment = new Comment("1", "editcomment", book);

        given(bookService.findById("1")).willReturn(Optional.ofNullable(bookMapper.toDTO(book)));
        given(bookRepository.findById("1")).willReturn(Optional.of(book));
        given(commentRepository.findById("1")).willReturn(Optional.of(expectedComment));
        given(commentRepository.save(expectedComment)).willReturn(expectedComment);
        CommentDto actualComment = commentService.update("1", "editcomment", "1");
        assertEquals(commentMapper.toDTO(expectedComment), actualComment);
        verify(commentRepository, times(1)).save(eq(expectedComment));
    }

    @DisplayName("должен корректно удалять комментарий по идентификатору")
    @Test
    void shouldCorrectDeleteById() {
        doNothing().when(commentRepository).deleteById(eq("1"));
        commentService.deleteById("1");
        verify(commentRepository, times(1)).deleteById(eq("1"));
    }
}
