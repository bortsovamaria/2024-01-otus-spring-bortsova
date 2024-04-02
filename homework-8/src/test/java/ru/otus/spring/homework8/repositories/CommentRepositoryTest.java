package ru.otus.spring.homework8.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.models.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Репозиторий комментариев должен ")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.homework8.config", "ru.otus.spring.homework8.repositories"})
class CommentRepositoryTest {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("возвращать все комментарии по id книги")
    @Test
    void shouldCorrectFindCommentsByBookId() {
        Optional<Book> book = bookRepository.findById("1");
        if (book.isPresent()) {
            List<Comment> commentsByBookId = commentRepository.findAllByBookId(book.get().getId());
            assertThat(commentsByBookId).hasSize(2);
        }
    }

}