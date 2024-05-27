package ru.otus.spring.homework14.config.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.spring.homework14.models.sql.Book;
import ru.otus.spring.homework14.models.nosql.BookDocument;

import static ru.otus.spring.homework14.config.BatchConfig.CHUNK_SIZE;

@RequiredArgsConstructor
@Component
public class BookMigrationStep {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step transformBooksStep(ItemReader<Book> bookItemReader,
                                   ItemProcessor<Book, BookDocument> bookProcessor,
                                   ItemWriter<BookDocument> bookDocumentWriter) {
        return new StepBuilder("booksLoad", jobRepository)
                .<Book, BookDocument>chunk(CHUNK_SIZE, transactionManager)
                .reader(bookItemReader)
                .processor(bookProcessor)
                .writer(bookDocumentWriter)
                .build();
    }
}
