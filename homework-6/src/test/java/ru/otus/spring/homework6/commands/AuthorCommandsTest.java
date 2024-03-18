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
import ru.otus.spring.homework6.utils.AuthorUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("Тест команд shell для авторов ")
@SpringBootTest
class AuthorCommandsTest {

    private static final String COMMAND_FIND_ALL_AUTHORS = "aa";

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

    @DisplayName(" должен возвращать список авторов")
    @Test
    void shouldReturnCorrectAllAuthors() throws Exception {
        when(inputProvider.readInput())
                .thenReturn(() -> COMMAND_FIND_ALL_AUTHORS)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(1)).handle(argumentCaptor.capture());
        List<Object> results = argumentCaptor.getAllValues();
        assertThat(results).contains(AuthorUtils.ALL_AUTHORS_RESULT);
    }
}