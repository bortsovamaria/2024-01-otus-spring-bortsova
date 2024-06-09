package ru.otus.spring.homework16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.printf("Чтобы посмотреть custom метрику, необходимо перейти: %n%s%n",
                "http://localhost:8080/actuator");
    }
}
