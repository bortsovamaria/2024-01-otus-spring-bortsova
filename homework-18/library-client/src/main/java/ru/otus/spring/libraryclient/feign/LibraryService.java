package ru.otus.spring.libraryclient.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.libraryclient.dto.BookDto;
import ru.otus.spring.libraryclient.exceptions.EntityNotFoundException;

import java.util.List;

@Service
@FeignClient(name = "library-service")
public interface LibraryService {
    @CircuitBreaker(name = "library-service", fallbackMethod = "getAllFallback")
    @GetMapping(value = "/api/books")
    List<BookDto> getAllBooks();

    default List<BookDto> getAllFallback(Throwable t) {
        throw new EntityNotFoundException("Books not found");
    }
}
