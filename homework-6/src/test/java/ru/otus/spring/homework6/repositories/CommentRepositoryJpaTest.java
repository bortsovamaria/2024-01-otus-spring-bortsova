package ru.otus.spring.homework6.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.homework6.models.Author;
import ru.otus.spring.homework6.models.Book;
import ru.otus.spring.homework6.models.Comment;
import ru.otus.spring.homework6.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать комментарий по id")
    @Test
    void shouldFindCommentById() {
        val optionalActualStudent = repositoryJpa.findById(1L);
        val expectedStudent = em.find(Comment.class, 1L);
        assertThat(optionalActualStudent).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedStudent);
    }

    @DisplayName("должен добавлять новый комментарий")
    @Test
    void shouldSaveNewComment() {
        val author = new Author(1L, "Author_1");
        val genre = new Genre(1L, "Genre_1");
        val book = new Book(1L, "title", author, genre);

        val comment = new Comment(0, "text", book);

        repositoryJpa.save(comment);
        assertThat(comment.getId()).isGreaterThan(0);

        val actualBook = em.find(Comment.class, comment.getId());
        assertThat(actualBook).isNotNull().matches(s -> !s.getText().isEmpty())
                .matches(s -> s.getBook().getId() > 0);
    }

    @DisplayName("должен обновлять комментарий")
    @Test
    void shouldUpdatedComment() {
        Comment initialComment = em.find(Comment.class, 1L);
        Book book =
                new Book(1L, "title",
                        new Author(1L, "Author_1"),
                        new Genre(1L, "Genre_1"));

        Comment expectedComment = new Comment(1L, "edit", book);
        em.detach(initialComment);

        repositoryJpa.save(expectedComment);
        val updatedComment = em.find(Comment.class, 1L);

        assertThat(updatedComment.getText())
                .isNotEqualTo(initialComment.getText())
                .isEqualTo(expectedComment.getText());
    }

    @DisplayName("должен удалять комментарий по id ")
    @Test
    void shouldDeleteComment() {
        val comment = em.find(Comment.class, 2L);
        assertThat(comment).isNotNull();
        em.detach(comment);

        repositoryJpa.deleteById(2L);
        val deletedComment = em.find(Comment.class, 2L);

        assertThat(deletedComment).isNull();
    }
}