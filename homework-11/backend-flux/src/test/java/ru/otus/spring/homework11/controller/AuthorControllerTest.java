package ru.otus.spring.homework11.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootTest
class AuthorControllerTest {

    @Autowired
    private RouterFunction<ServerResponse> route;

    @Test
    void shouldReturnCorrectAuthorList() throws Exception {

        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/authors")
                .exchange()
                .expectStatus()
                .isOk();
    }
}