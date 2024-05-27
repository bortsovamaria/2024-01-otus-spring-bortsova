package ru.otus.spring.homework14.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework14.models.sql.Author;
import ru.otus.spring.homework14.models.sql.Book;
import ru.otus.spring.homework14.models.sql.Genre;

import static ru.otus.spring.homework14.config.BatchConfig.MAXIMUM_READ_COUNT;

@RequiredArgsConstructor
@Component
public class EntityItemReaders {


    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public ItemReader<Author> authorItemReader() {
        return new JpaCursorItemReaderBuilder<Author>()
                .name("authorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("FROM Author")
                .maxItemCount(MAXIMUM_READ_COUNT)
                .build();
    }

    @Bean
    public ItemReader<Genre> genreItemReader() {
        return new JpaCursorItemReaderBuilder<Genre>()
                .name("genreItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("FROM Genre")
                .maxItemCount(MAXIMUM_READ_COUNT)
                .build();
    }

    @Bean
    public ItemReader<Book> bookItemReader() {
        return new JpaCursorItemReaderBuilder<Book>()
                .name("bookItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("FROM Book")
                .maxItemCount(MAXIMUM_READ_COUNT)
                .build();
    }
}