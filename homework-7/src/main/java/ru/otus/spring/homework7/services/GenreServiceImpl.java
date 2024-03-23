package ru.otus.spring.homework7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework7.models.Genre;
import ru.otus.spring.homework7.repositories.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
