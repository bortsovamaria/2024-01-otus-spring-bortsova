package ru.otus.spring.homework11.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.otus.spring.homework11.dto.AuthorDto;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnCorrectAuthorList() {

        var client = WebClient.create(String.format("http://localhost:%d", port));
        var expectedSize = 5;

        //when
        List<AuthorDto> result = client
                .get().uri("/api/authors")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(AuthorDto.class)
                .take(expectedSize)
                .timeout(Duration.ofSeconds(3))
                .collectList()
                .block();

        //then
        assertThat(result).hasSize(3)
                .contains(
                        new AuthorDto(1L, "Author_1"),
                        new AuthorDto(2L, "Author_2"),
                        new AuthorDto(3L, "Author_3")
                );
    }
}