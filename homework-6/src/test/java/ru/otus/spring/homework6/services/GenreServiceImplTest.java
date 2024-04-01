package ru.otus.spring.homework6.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework6.models.Genre;
import ru.otus.spring.homework6.repositories.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.homework6.utils.GenreUtils.getExpectedGenres;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис для работы с жанрами")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @MockBean
    GenreRepository genreRepository;

    @Autowired
    GenreService genreService;

    @DisplayName("Должен корректно находить все жанры")
    @Test
    void findAll() {
        given(genreRepository.findAll()).willReturn(getExpectedGenres());
        List<Genre> actualGenres = genreService.findAll();
        assertEquals(actualGenres, getExpectedGenres());
        verify(genreRepository, times(1)).findAll();
    }
}