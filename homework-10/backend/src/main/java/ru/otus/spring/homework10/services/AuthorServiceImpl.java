package ru.otus.spring.homework10.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework10.models.Author;
import ru.otus.spring.homework10.repositories.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
