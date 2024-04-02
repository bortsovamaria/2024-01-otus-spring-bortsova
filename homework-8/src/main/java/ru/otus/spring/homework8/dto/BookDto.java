package ru.otus.spring.homework8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {

    private String id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;

    private List<CommentDto> comments;
}
