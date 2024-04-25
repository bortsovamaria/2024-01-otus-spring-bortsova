package ru.otus.spring.homework10.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework10.dto.mapper.AuthorMapperImpl;
import ru.otus.spring.homework10.models.Author;
import ru.otus.spring.homework10.repositories.AuthorRepository;
import ru.otus.spring.homework10.utils.AuthorUtils;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с авторами ")
@SpringBootTest(classes = {AuthorServiceImpl.class, AuthorMapperImpl.class})
class AuthorServiceImplTest {

    @MockBean
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @DisplayName("должен корректно находить всех авторов")
    @Test
    void shouldCorrectFindAllAuthors() {
        given(authorRepository.findAll()).willReturn(AuthorUtils.getExpectedAuthors());
        List<Author> actualAuthors = authorService.findAll();
        Assertions.assertEquals(actualAuthors, AuthorUtils.getExpectedAuthors());
        verify(authorRepository, times(1)).findAll();
    }
}