package ru.otus.spring.homework11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.homework11.dto.AuthorDto;
import ru.otus.spring.homework11.dto.mapper.AuthorMapper;
import ru.otus.spring.homework11.repositories.AuthorRepository;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @GetMapping("/api/authors")
    public Flux<AuthorDto> findAllAuthors() {
        return authorRepository.findAll().map(authorMapper::toDTO);
    }
}
