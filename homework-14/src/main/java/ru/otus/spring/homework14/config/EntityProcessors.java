package ru.otus.spring.homework14.config;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework14.models.sql.Author;
import ru.otus.spring.homework14.models.sql.Book;
import ru.otus.spring.homework14.models.sql.Genre;
import ru.otus.spring.homework14.models.nosql.AuthorDocument;
import ru.otus.spring.homework14.models.nosql.BookDocument;
import ru.otus.spring.homework14.models.nosql.GenreDocument;

import java.util.Optional;

@Component
public class EntityProcessors {

    @Bean
    public ItemProcessor<Author, AuthorDocument> authorProcessor() {
        return authorItem -> new AuthorDocument(ObjectId.get().toString(), authorItem.getFullName());
    }

    @Bean
    public ItemProcessor<Genre, GenreDocument> genreProcessor() {
        return genreItem -> new GenreDocument(ObjectId.get().toString(), genreItem.getName());
    }

    @Bean
    public ItemProcessor<Book, BookDocument> bookProcessor() {
        return bookItem -> {
            var genre = Optional.ofNullable(bookItem.getGenre())
                    .map(g -> new GenreDocument(ObjectId.get().toString(), g.getName()))
                    .orElse(null);
            var author = Optional.ofNullable(bookItem.getAuthor())
                    .map(a -> new AuthorDocument(ObjectId.get().toString(), a.getFullName()))
                    .orElse(null);
            return new BookDocument(ObjectId.get().toString(), bookItem.getTitle(), author, genre);
        };
    }
}