package ru.otus.spring.homework7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework7.converters.GenreConverter;
import ru.otus.spring.homework7.dto.GenreDto;
import ru.otus.spring.homework7.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private final GenreConverter genreConverter;

    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream().map(genreConverter::toDto).collect(Collectors.toList());
    }
}
