package ru.otus.spring.homework8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework8.dto.AuthorDto;
import ru.otus.spring.homework8.dto.mapper.AuthorMapper;
import ru.otus.spring.homework8.repositories.AuthorRepository;

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
