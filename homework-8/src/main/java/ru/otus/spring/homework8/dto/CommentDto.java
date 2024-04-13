package ru.otus.spring.homework8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

    private String id;

    private String text;
}
