package ru.otus.spring.homework8.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework8.dto.AuthorDto;
import ru.otus.spring.homework8.dto.mapper.AuthorMapper;
import ru.otus.spring.homework8.dto.mapper.AuthorMapperImpl;
import ru.otus.spring.homework8.repositories.AuthorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.homework8.utils.AuthorUtils.getExpectedAuthors;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с авторами ")
@SpringBootTest(classes = {AuthorServiceImpl.class, AuthorMapperImpl.class})
class AuthorServiceImplTest {

    @MockBean
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorMapper authorMapper;

    @DisplayName("должен корректно находить всех авторов")
    @Test
    void shouldCorrectFindAllAuthors() {
        given(authorRepository.findAll()).willReturn(getExpectedAuthors());
        List<AuthorDto> actualAuthors = authorService.findAll();
        assertEquals(actualAuthors, getExpectedAuthors().stream().map(authorMapper::toDTO).toList());
        verify(authorRepository, times(1)).findAll();
    }
}