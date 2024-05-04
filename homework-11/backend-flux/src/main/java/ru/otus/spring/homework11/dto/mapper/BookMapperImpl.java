package ru.otus.spring.homework11.dto.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework11.dto.AuthorDto;
import ru.otus.spring.homework11.dto.BookCreateDto;
import ru.otus.spring.homework11.dto.BookUpdateDto;
import ru.otus.spring.homework11.dto.GenreDto;
import ru.otus.spring.homework11.models.Author;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Genre;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookUpdateDto toUpdateDTO(Book book, Author author, Genre genre) {
        return new BookUpdateDto(book.getId(),
                book.getTitle(),
                new AuthorDto(author.getId(), author.getFullName()),
                new GenreDto(genre.getId(), genre.getName()));
    }

    @Override
    public BookCreateDto toCreateDTO(Book book, Author author, Genre genre) {
        return new BookCreateDto(book.getTitle(),
                new AuthorDto(author.getId(), author.getFullName()),
                new GenreDto(genre.getId(), genre.getName()));
    }

    @Override
    public Book toDomainUpdateDTO(BookUpdateDto bookUpdateDto) {
        return new Book(bookUpdateDto.getId(), bookUpdateDto.getTitle(), bookUpdateDto.getAuthor().getId(), bookUpdateDto.getGenre().getId());
    }

    @Override
    public Book toDomainCreateDTO(BookCreateDto bookCreateDto) {
        return new Book(bookCreateDto.getTitle(), bookCreateDto.getAuthor().getId(), bookCreateDto.getGenre().getId());
    }
}
