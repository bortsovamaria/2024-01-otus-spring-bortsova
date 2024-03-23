package ru.otus.spring.homework7.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework7.models.Comment;

@Component
public class CommentConverter {

    public String commentToString(Comment comment) {
        return "Id: %d, text: %s"
                .formatted(
                        comment.getId(),
                        comment.getText());
    }
}
