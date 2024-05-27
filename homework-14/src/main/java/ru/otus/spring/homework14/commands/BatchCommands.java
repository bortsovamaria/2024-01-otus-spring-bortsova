package ru.otus.spring.homework14.commands;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework14.converters.AuthorConverter;
import ru.otus.spring.homework14.converters.BookConverter;
import ru.otus.spring.homework14.converters.GenreConverter;
import ru.otus.spring.homework14.models.nosql.AuthorDocument;
import ru.otus.spring.homework14.models.nosql.BookDocument;
import ru.otus.spring.homework14.models.nosql.GenreDocument;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final JobLauncher jobLauncher;

    private final Job migrateToNoSqlJob;

    private final MongoOperations mongoOperations;

    private final BookConverter bookConverter;

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        jobLauncher.run(migrateToNoSqlJob, new JobParameters());
    }

    @ShellMethod(value = "getMongoBooks", key = "mb")
    public String getMongoBooks() {
        var books = mongoOperations.find(new Query(), BookDocument.class);

        return books.stream().map(bookConverter::mongoBookToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "getMongoAuthors", key = "ma")
    public String getMongoAuthors() {
        var authors = mongoOperations.find(new Query(), AuthorDocument.class);

        return authors.stream().map(authorConverter::mongoAuthorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "getMongoGenres", key = "mg")
    public String getMongoGenres() {
        var genres = mongoOperations.find(new Query(), GenreDocument.class);

        return genres.stream().map(genreConverter::mongoGenreToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }
}
