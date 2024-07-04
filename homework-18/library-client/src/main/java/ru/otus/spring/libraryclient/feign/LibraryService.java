package ru.otus.spring.libraryclient.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.libraryclient.dto.AuthorDto;
import ru.otus.spring.libraryclient.dto.BookDto;
import ru.otus.spring.libraryclient.dto.GenreDto;

import java.util.List;

@Service
@FeignClient(name = "library-service")
public interface LibraryService {
    @CircuitBreaker(name = "library-service", fallbackMethod = "buildFallbackBooks")
    @GetMapping(value = "/api/books")
    List<BookDto> getAllBooks();

    default List<BookDto> buildFallbackBooks(String animal) {
        BookDto bookDto = new BookDto();
        bookDto.setId(0L);
        bookDto.setTitle("N/A");
        bookDto.setAuthor(new AuthorDto(0L, "N/A"));
        bookDto.setGenre(new GenreDto(0L, "N/A"));
        return List.of(bookDto);
    }
}
