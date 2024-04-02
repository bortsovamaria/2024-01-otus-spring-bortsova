package ru.otus.spring.homework8.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.homework8.dto.CommentDto;
import ru.otus.spring.homework8.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDTO(Comment comment);
}
