package ru.otus.spring.homework8.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework8.dto.BookDto;
import ru.otus.spring.homework8.dto.BookPartDto;

@RequiredArgsConstructor
@Component
public class BookConverter {
    public String bookDtoToString(BookDto bookDto) {
        return "Id: %s, title: %s, author: {%s}, genres: [%s]".formatted(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre());
    }

    public String bookDtoToString(BookPartDto bookDto) {
        return "Id: %s, title: %s, author: {%s}, genres: [%s]".formatted(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre());
    }
}
