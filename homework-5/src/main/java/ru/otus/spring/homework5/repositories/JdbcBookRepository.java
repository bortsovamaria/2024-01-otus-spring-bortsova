package ru.otus.spring.homework5.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework5.exceptions.EntityNotFoundException;
import ru.otus.spring.homework5.models.Author;
import ru.otus.spring.homework5.models.Book;
import ru.otus.spring.homework5.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private static final String FIND_BY_ID_QUERY = "select * from books inner join authors " +
            "on authors.id = books.author_id " +
            "inner join genres on genres.id = books.genre_id " +
            "where books.id = :id";

    private static final String INSERT_BOOK_QUERY = "insert into books (title, author_id, genre_id) " +
            "values (:title, :author_id, :genre_id)";

    private static final String FIND_ALL_QUERY = "select * from books " +
            "inner join authors on authors.id = books.author_id " +
            "inner join genres on genres.id = books.genre_id";

    private static final String UPDATE_QUERY = "update books set " +
            "title = :title, author_id = :author_id, genre_id = :genre_id where id = :id";

    private static final String DELETE_QUERY = "delete from books where id = :id";

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
                    FIND_BY_ID_QUERY,
                    params, new BookRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query(FIND_ALL_QUERY,
                new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(DELETE_QUERY, params);
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());

        namedParameterJdbcOperations.update(INSERT_BOOK_QUERY, parameters, keyHolder);
        //noinspection DataFlowIssue
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        try {
            namedParameterJdbcOperations
                    .update(UPDATE_QUERY,
                            Map.of("title", book.getTitle(),
                                    "author_id", book.getAuthor().getId(),
                                    "genre_id", book.getGenre().getId(),
                                    "id", book.getId())
                    );
            return book;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Not found book entity");
        }
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Book book = new Book();
            Author author = new Author();
            Genre genre = new Genre();

            author.setId(resultSet.getLong("author_id"));
            author.setFullName(resultSet.getString("full_name"));

            genre.setId(resultSet.getLong("genre_id"));
            genre.setName(resultSet.getString("name"));

            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(author);
            book.setGenre(genre);
            return book;
        }
    }
}
