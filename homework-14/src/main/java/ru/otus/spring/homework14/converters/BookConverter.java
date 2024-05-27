package ru.otus.spring.homework14.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework14.models.nosql.BookDocument;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    public String mongoBookToString(BookDocument book) {
        return "Id: %s, Title: %s, Author: {%s}, Genre: [%s]".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.mongoAuthorToString(book.getAuthor()),
                genreConverter.mongoGenreToString(book.getGenre()));
    }
}