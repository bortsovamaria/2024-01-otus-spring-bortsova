package ru.otus.spring.homework5.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework5.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {

    private static final String FIND_ALL_QUERY = "select id, name from genres";

    private static final String FIND_BY_ID_QUERY = "select id, name from genres where id = :id";


    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Genre> findAll() {
        return jdbc.query(FIND_ALL_QUERY, new GenreRowMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(FIND_BY_ID_QUERY,
                params, new GenreRowMapper()));
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
