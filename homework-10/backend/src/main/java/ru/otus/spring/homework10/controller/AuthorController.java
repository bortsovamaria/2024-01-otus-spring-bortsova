package ru.otus.spring.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework10.dto.AuthorDto;
import ru.otus.spring.homework10.dto.mapper.AuthorMapper;
import ru.otus.spring.homework10.services.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @GetMapping("/api/authors")
    public List<AuthorDto> findAllBooks() {
        return authorService.findAll().stream().map(authorMapper::toDTO).collect(Collectors.toList());
    }
}
