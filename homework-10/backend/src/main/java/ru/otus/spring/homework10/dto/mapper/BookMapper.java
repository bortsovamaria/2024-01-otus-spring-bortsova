package ru.otus.spring.homework10.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework10.dto.BookDto;
import ru.otus.spring.homework10.models.Book;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDTO(Book book);

    Book toDomain(BookDto bookDto);
}
