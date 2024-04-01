package ru.otus.spring.homework7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework7.dto.AuthorDto;
import ru.otus.spring.homework7.dto.mapper.AuthorMapper;
import ru.otus.spring.homework7.repositories.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDTO)
                .toList();
    }
}
