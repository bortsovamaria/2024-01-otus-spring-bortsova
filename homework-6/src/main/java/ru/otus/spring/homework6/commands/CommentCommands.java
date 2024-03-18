package ru.otus.spring.homework6.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework6.converters.CommentConverter;
import ru.otus.spring.homework6.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    //ci 1
    @ShellMethod(value = "Find comment by id", key = "ci")
    public String findCommentById(long id) {
        return commentService.findById(id)
                .map(commentConverter::commentToString)
                .orElse("Comment with id %d not found".formatted(id));
    }

    //cbi 1
    @ShellMethod(value = "Find comment by book id", key = "cbi")
    public String findCommentByBookId(long id) {
        return commentService.findByBookId(id)
                .stream()
                .map(commentConverter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    // acbi 1 comment_text
    @ShellMethod(value = "Add comment by book id", key = "acbi")
    public void addCommentByBookId(long bookId, String text) {
      commentService.saveComment(bookId, text);
    }
}
