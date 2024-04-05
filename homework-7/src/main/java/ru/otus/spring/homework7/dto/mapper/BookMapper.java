package ru.otus.spring.homework7.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.dto.BookDto;
import ru.otus.spring.homework7.dto.BookPartDto;
import ru.otus.spring.homework7.models.Book;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDTO(Book book);

    BookPartDto toPartDTO(Book book);
}
