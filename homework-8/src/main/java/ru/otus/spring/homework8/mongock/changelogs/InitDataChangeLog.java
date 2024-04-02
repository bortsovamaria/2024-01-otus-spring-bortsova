package ru.otus.spring.homework8.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.homework8.models.Author;
import ru.otus.spring.homework8.models.Book;
import ru.otus.spring.homework8.models.Comment;
import ru.otus.spring.homework8.models.Genre;
import ru.otus.spring.homework8.repositories.AuthorRepository;
import ru.otus.spring.homework8.repositories.BookRepository;
import ru.otus.spring.homework8.repositories.CommentRepository;
import ru.otus.spring.homework8.repositories.GenreRepository;

@ChangeLog(order = "001")
public class InitDataChangeLog {

    private Author firstAuthor;

    private Author secondAuthor;

    private Author thirdAuthor;

    private Genre firstGenre;

    private Genre secondGenre;

    private Genre thirdGenre;

    private Book firstBook;

    private Book secondBook;

    @ChangeSet(order = "000", id = "dropDataBase", author = "mbortsova", runAlways = true)
    public void dropTables(MongoDatabase database) {
        database.drop();
    }


    @ChangeSet(order = "001", id = "initAuthors", author = "mbortsova", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        firstAuthor = repository.save(new Author("1", "Author_1"));
        secondAuthor = repository.save(new Author("2","Author_2"));
        thirdAuthor = repository.save(new Author("3","Author_3"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "mbortsova", runAlways = true)
    public void initGenres(GenreRepository repository) {
        firstGenre = repository.save(new Genre("1","Genre_1"));
        secondGenre = repository.save(new Genre("2","Genre_2"));
        thirdGenre = repository.save(new Genre("3","Genre_3"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "mbortsova", runAlways = true)
    public void initBooks(BookRepository repository) {
        firstBook = repository.save(new Book("1", "BookTitle_1", firstAuthor, firstGenre));
        secondBook = repository.save(new Book("2", "BookTitle_2", secondAuthor, secondGenre));
        repository.save(new Book("3", "BookTitle_3", thirdAuthor, thirdGenre));
    }

    @ChangeSet(order = "004", id = "initComments", author = "mbortsova", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment("1", "Comment 1", firstBook));
        repository.save(new Comment("2", "Comment 2", firstBook));
        repository.save(new Comment("3", "Comment 3", secondBook));
    }
}
