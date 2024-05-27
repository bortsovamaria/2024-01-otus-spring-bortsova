package ru.otus.spring.homework14.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.spring.homework14.models.nosql.AuthorDocument;
import ru.otus.spring.homework14.models.nosql.BookDocument;
import ru.otus.spring.homework14.models.nosql.GenreDocument;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
public class BookMigrationJobTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private MongoOperations mongoOperations;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();

        mongoOperations.dropCollection(BookDocument.class);
        mongoOperations.dropCollection(AuthorDocument.class);
        mongoOperations.dropCollection(GenreDocument.class);
    }

    @Test
    void testJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo("migrateToNoSqlJob");

        var jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        var books = mongoOperations.findAll(BookDocument.class);
        var genres = mongoOperations.findAll(GenreDocument.class);
        var authors = mongoOperations.findAll(AuthorDocument.class);

        assertThat(authors).hasSize(3);
        assertThat(genres).hasSize(3);
        assertThat(books).hasSize(3);

    }
}