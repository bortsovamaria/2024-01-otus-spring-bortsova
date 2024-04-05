package ru.otus.spring.homework7.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.dto.CommentDto;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    public String commentDtoToString(CommentDto commentDto) {
        return "Id: %d, text: %s".formatted(
                commentDto.getId(),
                commentDto.getText()
        );
    }
}