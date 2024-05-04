package ru.otus.spring.homework10.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework10.dto.mapper.GenreMapper;
import ru.otus.spring.homework10.models.Genre;
import ru.otus.spring.homework10.services.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    @MockBean
    private GenreMapper genreMapper;

    @Test
    void shouldReturnCorrectAuthorList() throws Exception {
        List<Genre> genres = List.of(new Genre(1L, "genre1"));
        given(genreService.findAll()).willReturn(genres);

        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andReturn();
    }
}