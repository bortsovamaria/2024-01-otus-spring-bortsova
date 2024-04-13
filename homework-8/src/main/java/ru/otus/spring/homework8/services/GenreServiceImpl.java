package ru.otus.spring.homework8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework8.dto.GenreDto;
import ru.otus.spring.homework8.dto.mapper.GenreMapper;
import ru.otus.spring.homework8.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream().map(genreMapper::toDTO).collect(Collectors.toList());
    }
}
