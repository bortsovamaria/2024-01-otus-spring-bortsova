package ru.otus.spring.homework11.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.otus.spring.homework11.dto.GenreDto;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GenreControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnCorrectAuthorList() {

        var client = WebClient.create(String.format("http://localhost:%d", port));
        var expectedSize = 3;

        //when
        List<GenreDto> result = client
                .get().uri("/api/genres")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(GenreDto.class)
                .take(expectedSize)
                .timeout(Duration.ofSeconds(3))
                .collectList()
                .block();

        //then
        assertThat(result).hasSize(3)
                .contains(
                        new GenreDto(1L, "Genre_1"),
                        new GenreDto(2L, "Genre_2"),
                        new GenreDto(3L, "Genre_3")
                );
    }
}