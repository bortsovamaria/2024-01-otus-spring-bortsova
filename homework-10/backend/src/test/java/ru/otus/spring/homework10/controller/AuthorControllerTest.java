package ru.otus.spring.homework10.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework10.dto.mapper.AuthorMapper;
import ru.otus.spring.homework10.models.Author;
import ru.otus.spring.homework10.services.AuthorService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private AuthorMapper authorMapper;

    @Test
    void shouldReturnCorrectAuthorList() throws Exception {
        List<Author> authors = List.of(new Author(1L, "author1"));
        given(authorService.findAll()).willReturn(authors);

        mvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andReturn();
    }
}