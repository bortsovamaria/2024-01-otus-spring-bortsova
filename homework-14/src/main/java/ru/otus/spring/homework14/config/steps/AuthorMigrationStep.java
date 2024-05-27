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
import ru.otus.spring.homework14.models.sql.Author;
import ru.otus.spring.homework14.models.nosql.AuthorDocument;

import static ru.otus.spring.homework14.config.BatchConfig.CHUNK_SIZE;

@RequiredArgsConstructor
@Component
public class AuthorMigrationStep {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step transformAuthorsStep(ItemReader<Author> authorItemReader,
                                     ItemProcessor<Author, AuthorDocument> authorProcessor,
                                     ItemWriter<AuthorDocument> authorDocumentWriter) {
        return new StepBuilder("authorsLoad", jobRepository)
                .<Author, AuthorDocument>chunk(CHUNK_SIZE, transactionManager)
                .reader(authorItemReader)
                .processor(authorProcessor)
                .writer(authorDocumentWriter)
                .build();
    }
}
