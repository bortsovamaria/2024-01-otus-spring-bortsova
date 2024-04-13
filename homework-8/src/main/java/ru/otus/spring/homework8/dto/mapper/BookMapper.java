package ru.otus.spring.homework8.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework8.dto.BookDto;
import ru.otus.spring.homework8.dto.BookPartDto;
import ru.otus.spring.homework8.models.Book;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDTO(Book book);

    BookPartDto toPartDTO(Book book);
}
