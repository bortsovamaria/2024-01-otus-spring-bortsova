package ru.otus.spring.homework8.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework8.converters.CommentConverter;
import ru.otus.spring.homework8.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    //ci 1
    @ShellMethod(value = "Find comment by id", key = "ci")
    public String findCommentById(String id) {
        return commentService.findById(id)
                .map(commentConverter::commentDtoToString)
                .orElse("Comment with id %s not found".formatted(id));
    }

    //cbi 1
    @ShellMethod(value = "Find comment by book id", key = "cbi")
    public String findCommentByBookId(String id) {
        return commentService.findByBookId(id)
                .stream()
                .map(commentConverter::commentDtoToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    // acbi newComment 1
    @ShellMethod(value = "Add comment by book id", key = "acbi")
    public void addCommentByBookId(String text, String bookId) {
        commentService.insert(text, bookId);
    }

    // cupd 4 editComment 1
    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(String id, String text, String bookId) {
        var savedComment = commentService.update(id, text, bookId);
        return commentConverter.commentDtoToString(savedComment);
    }

    // cdel 4
    @ShellMethod(value = "Delete book by id", key = "cdel")
    public void deleteBook(String id) {
        commentService.deleteById(id);
    }
}
