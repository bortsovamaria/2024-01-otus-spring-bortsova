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
import ru.otus.spring.homework6.utils.BookUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Тест команд shell для книг ")
@SpringBootTest
class BookCommandsTest {

    private static final String COMMAND_FIND_ALL_BOOKS = "ab";
    private static final String COMMAND_FIND_BOOK_BY_ID = "bbid 1";
    private static final String COMMAND_INSERT_BOOK = "bins newBook 1 1";
    private static final String COMMAND_UPDATE_BOOK = "bupd 3 editedBook 3 2";
    private static final String COMMAND_DELETE_BOOK = "bdel 4";

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

    @DisplayName("должен загружать список всех книг")
    @Test
    void shouldReturnCorrectAllBooks() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_FIND_ALL_BOOKS)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(BookUtils.ALL_BOOK_RESULT);
    }

    @DisplayName("должен загружать книгу по id")
    @Test
    void shouldReturnCorrectBookById() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_FIND_BOOK_BY_ID)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(BookUtils.BOOK_BY_ID_RESULT);
    }

    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_INSERT_BOOK)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(BookUtils.ADD_NEW_BOOK_RESULT);
    }

    @DisplayName("должен обновить книгу")
    @Test
    void shouldUpdatedBook() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_UPDATE_BOOK)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(BookUtils.EDIT_BOOK_RESULT);
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_DELETE_BOOK)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
    }
}