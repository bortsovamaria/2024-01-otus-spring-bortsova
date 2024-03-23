package ru.otus.spring.homework7.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.homework7.models.Author;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать книгу по id")
    @Test
    void shouldReturnCorrectBookById() {
        val optionalActualBook = repositoryJpa.findById(1L);
        val expectedBook = em.find(Book.class, 1L);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг")
    @Test
    void shouldReturnCorrectBooksList() {
        val students = repositoryJpa.findAll();
        assertThat(students).isNotNull().hasSize(3)
                .allMatch(s -> s.getTitle() != null)
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);

    }

    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        val author = new Author(0, "Author_1");
        val genre = new Genre(0, "Genre_1");
        val book = new Book(0, "title", author, genre);

        repositoryJpa.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(s -> !s.getTitle().isEmpty())
                .matches(s -> s.getGenre() != null)
                .matches(s -> s.getAuthor() != null);
    }

    @DisplayName("должен обновлять книгу")
    @Test
    void shouldUpdatedBook() {
        Book initialBook = em.find(Book.class, 1L);
        Book expectedBook =
                new Book(1L, "new title",
                        new Author(1L, "Author_1"),
                        new Genre(1L, "Genre_1"));
        em.detach(initialBook);

        repositoryJpa.save(expectedBook);
        val updatedBook = em.find(Book.class, 1L);

        assertThat(updatedBook.getTitle())
                .isNotEqualTo(initialBook.getTitle())
                .isEqualTo(expectedBook.getTitle());
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        val book = em.find(Book.class, 2L);
        assertThat(book).isNotNull();
        em.detach(book);

        repositoryJpa.deleteById(2L);
        val deletedBook = em.find(Book.class, 2L);

        assertThat(deletedBook).isNull();
    }
}