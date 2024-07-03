package ru.otus.spring.libraryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.libraryservice.models.Genre;
import ru.otus.spring.libraryservice.repositories.GenreRepository;
import ru.otus.spring.libraryservice.services.GenreService;

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
