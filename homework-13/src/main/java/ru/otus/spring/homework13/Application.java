package ru.otus.spring.homework13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.printf("Чтобы перейти на страницу сайта открывайте: %n%s%n",
                "http://localhost:8080/books");
    }
}
