package ru.otus.spring.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework10.dto.GenreDto;
import ru.otus.spring.homework10.dto.mapper.GenreMapper;
import ru.otus.spring.homework10.services.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @GetMapping("/api/genres")
    public List<GenreDto> findAllBooks() {
        return genreService.findAll().stream().map(genreMapper::toDTO).collect(Collectors.toList());
    }
}
