package ru.otus.spring.libraryclient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.libraryclient.dto.BookDto;
import ru.otus.spring.libraryclient.feign.LibraryServiceProxy;

import java.util.List;

@Service
@Slf4j
@ConditionalOnProperty(name = "use", havingValue = "feign")
@RequiredArgsConstructor
public class BookService {

    private final LibraryServiceProxy feignProxy;

    public List<BookDto> findAll() {
        return feignProxy.getAllBooks();
    }

}