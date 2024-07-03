package ru.otus.spring.libraryclient.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.libraryclient.dto.AuthorDto;
import ru.otus.spring.libraryclient.dto.BookDto;

import java.util.Collections;
import java.util.List;

@Service
@FeignClient(name = "library-service")
public interface LibraryServiceProxy {
    @CircuitBreaker(name = "library-service", fallbackMethod = "fallbackGetAllBooks")
    @GetMapping(value = "/api/books")
    List<BookDto> getAllBooks();


    default List<AuthorDto> fallbackGetAllBooks(Throwable throwable) {
        return Collections.emptyList();
    }

}
