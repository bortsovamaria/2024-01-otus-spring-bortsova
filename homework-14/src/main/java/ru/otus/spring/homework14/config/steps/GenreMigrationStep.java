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
import ru.otus.spring.homework14.models.sql.Genre;
import ru.otus.spring.homework14.models.nosql.GenreDocument;

import static ru.otus.spring.homework14.config.BatchConfig.CHUNK_SIZE;

@RequiredArgsConstructor
@Component
public class GenreMigrationStep {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step transformGenresStep(ItemReader<Genre> genreItemReader,
                                    ItemProcessor<Genre, GenreDocument> genreProcessor,
                                    ItemWriter<GenreDocument> genreDocumentWriter) {
        return new StepBuilder("genresLoad", jobRepository)
                .<Genre, GenreDocument>chunk(CHUNK_SIZE, transactionManager)
                .reader(genreItemReader)
                .processor(genreProcessor)
                .writer(genreDocumentWriter)
                .build();
    }
}