package ru.otus.spring.homework7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookPartDto {

    private long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}
