package ru.otus.spring.homework11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.dto.BookCreateDto;
import ru.otus.spring.homework11.dto.BookUpdateDto;
import ru.otus.spring.homework11.dto.mapper.AuthorMapper;
import ru.otus.spring.homework11.dto.mapper.BookMapper;
import ru.otus.spring.homework11.dto.mapper.GenreMapper;
import ru.otus.spring.homework11.repositories.AuthorRepository;
import ru.otus.spring.homework11.repositories.BookRepository;
import ru.otus.spring.homework11.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;

    @GetMapping("/api/books")
    public Flux<BookUpdateDto> findAllBooks() {
        return bookRepository.findAll()
                .flatMap(b -> Mono.zip(
                        Mono.just(b),
                        authorRepository.findById(b.getAuthorId()),
                        genreRepository.findById(b.getGenreId())
                ))
                .map(tuple -> bookMapper.toUpdateDTO(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    @PostMapping("/api/books")
    public Mono<BookCreateDto> insertBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookRepository.save(bookMapper.toDomainCreateDTO(bookCreateDto))
                .map(b ->
                        bookMapper.toCreateDTO(b, authorMapper.toDomain(bookCreateDto.getAuthor()),
                                genreMapper.toDomain(bookCreateDto.getGenre())
                ));
    }

    @PutMapping("/api/books")
    public Mono<BookUpdateDto> saveBook(@RequestBody BookUpdateDto bookUpdateDto) {
        return bookRepository.save(bookMapper.toDomainUpdateDTO(bookUpdateDto))
                .map(b ->
                        bookMapper.toUpdateDTO(b, authorMapper.toDomain(bookUpdateDto.getAuthor()),
                                genreMapper.toDomain(bookUpdateDto.getGenre())
                        ));

    }

    @DeleteMapping("/api/books/{id}")
    public Mono<Void> deleteBook(@PathVariable("id") long id) {
        return bookRepository.deleteById(id);
    }

}