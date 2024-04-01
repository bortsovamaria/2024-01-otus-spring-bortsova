package ru.otus.spring.homework7.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.dto.BookDto;
import ru.otus.spring.homework7.models.Book;

@RequiredArgsConstructor
@Component
public class BookConverter {

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    private final CommentConverter commentConverter;


    public String bookDtoToString(BookDto bookDto) {
        return "Id: %d, title: %s, author: {%s}, genres: [%s]".formatted(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthorDto(),
                bookDto.getGenreDto());
    }

    public BookDto toDto(Book book) {
        var authorDto = authorConverter.toDto(book.getAuthor());
        var genreDto = genreConverter.toDto(book.getGenre());
        var commentsDto = book.getComments().stream().map(commentConverter::toDto).toList();
        return new BookDto(
                book.getId(),
                book.getTitle(),
                authorDto,
                genreDto,
                commentsDto
        );
    }

}
