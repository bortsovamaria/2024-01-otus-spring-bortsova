package ru.otus.spring.homework11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.homework11.dto.GenreDto;
import ru.otus.spring.homework11.dto.mapper.GenreMapper;
import ru.otus.spring.homework11.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @GetMapping("/api/genres")
    public Flux<GenreDto> findAllGenres() {
        return genreRepository.findAll().map(genreMapper::toDTO);
    }
}
