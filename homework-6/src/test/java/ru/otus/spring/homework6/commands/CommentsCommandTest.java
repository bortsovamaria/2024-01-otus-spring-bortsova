package ru.otus.spring.homework6.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.shell.InputProvider;
import org.springframework.shell.ResultHandlerService;
import org.springframework.shell.Shell;
import ru.otus.spring.homework6.utils.CommentUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.spring.homework6.utils.CommentUtils.EDIT_COMMENT_RESULT;

@DisplayName("Тест команд shell для комментариев ")
@SpringBootTest
public class CommentsCommandTest {

    private static final String COMMAND_FIND_COMMENT_BY_ID = "ci 2";

    private static final String COMMAND_FIND_COMMENT_BY_BOOK_ID = "cbi 2";

    private static final String COMMAND_ADD_COMMENT_BY_BOOK_ID = "acbi text 2";
    private static final String COMMAND_UPDATE_COMMENT_BY_ID = "cupd 1 editComment 2";
    private static final String COMMAND_DELETE_COMMENT_BY_ID = "cdel 2";

    private InputProvider inputProvider;

    private ArgumentCaptor<Object> argumentCaptor;

    @SpyBean
    private ResultHandlerService resultHandlerService;

    @Autowired
    private Shell shell;

    @BeforeEach
    void setUp() {
        inputProvider = mock(InputProvider.class);
        argumentCaptor = ArgumentCaptor.forClass(Object.class);
    }

    @DisplayName("должен загружать комментарий по id")
    @Test
    void shouldReturnCorrectAllBooks() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_FIND_COMMENT_BY_ID)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(CommentUtils.COMMENT_BY_ID_RESULT);
    }

    @DisplayName("должен загружать комментарий по id книги")
    @Test
    void shouldReturnCorrectBookById() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_FIND_COMMENT_BY_BOOK_ID)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(CommentUtils.COMMENT_BY_BOOK_ID_RESULT);
    }

    @DisplayName("должен добавлять комментарий по id книги")
    @Test
    void shouldCorrectAddCommentBookById() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_ADD_COMMENT_BY_BOOK_ID)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
    }

    @DisplayName("должен обновить комментарий")
    @Test
    void shouldUpdatedBook() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_UPDATE_COMMENT_BY_ID)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(EDIT_COMMENT_RESULT);
    }

    @DisplayName("должен удалять комментарий по id ")
    @Test
    void shouldDeleteBook() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_DELETE_COMMENT_BY_ID)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
    }
}