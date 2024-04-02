package ru.otus.spring.homework8.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework8.dto.CommentDto;

@Component
public class CommentConverter {

    public String commentDtoToString(CommentDto commentDto) {
        return "Id: %s, text: %s".formatted(
                commentDto.getId(),
                commentDto.getText()
        );
    }
}
