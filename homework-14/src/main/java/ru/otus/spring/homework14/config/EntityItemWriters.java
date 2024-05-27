package ru.otus.spring.homework14.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework14.models.nosql.AuthorDocument;
import ru.otus.spring.homework14.models.nosql.BookDocument;
import ru.otus.spring.homework14.models.nosql.GenreDocument;

@RequiredArgsConstructor
@Component
public class EntityItemWriters {

    private final MongoOperations mongoOperations;


    @Bean
    public ItemWriter<AuthorDocument> authorDocumentWriter() {
        return new MongoItemWriterBuilder<AuthorDocument>()
                .template(mongoOperations)
                .collection("authors")
                .build();
    }

    @Bean
    public ItemWriter<GenreDocument> genreDocumentWriter() {
        return new MongoItemWriterBuilder<GenreDocument>()
                .template(mongoOperations)
                .collection("genres")
                .build();
    }

    @Bean
    public ItemWriter<BookDocument> bookDocumentWriter() {
        return new MongoItemWriterBuilder<BookDocument>()
                .template(mongoOperations)
                .collection("books")
                .build();
    }
}