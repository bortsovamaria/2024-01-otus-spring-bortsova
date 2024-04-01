package ru.otus.spring.homework7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;

    private List<CommentDto> comments;
}
