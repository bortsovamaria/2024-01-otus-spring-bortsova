package ru.otus.spring.homework8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookPartDto {

    private String id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}
