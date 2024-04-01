package ru.otus.spring.homework7.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.dto.CommentDto;
import ru.otus.spring.homework7.models.Comment;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    public String commentDtoToString(CommentDto commentDto) {
        return "Id: %d, text: %s".formatted(
                commentDto.getId(),
                commentDto.getNote()
        );
    }

    public CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText()
        );
    }
}