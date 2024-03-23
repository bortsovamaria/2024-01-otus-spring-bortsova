package ru.otus.spring.homework7.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.homework7.models.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с авторами ")
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать всех авторов")
    @Test
    void shouldReturnCorrectAuthorsList() {
        List<Author> authors = repositoryJpa.findAll();
        assertThat(authors).isNotNull().hasSize(3);
    }

    @DisplayName("должен загружать автора по идентификатору")
    @Test
    void shouldReturnCorrectAuthorById() {
        Optional<Author> optionalActualAuthor = repositoryJpa.findById(1L);
        Author expectedAuthor = em.find(Author.class, 1L);
        assertThat(optionalActualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}