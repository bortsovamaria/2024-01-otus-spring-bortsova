package ru.otus.spring.homework11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookUpdateDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}
