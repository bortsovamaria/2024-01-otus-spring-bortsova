package ru.otus.spring.libraryservice.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.libraryservice.repositories.AuthorRepository;

@Component
@RequiredArgsConstructor
public class AuthorsHealthIndicator implements HealthIndicator {

    private final AuthorRepository authorRepository;

    @Override
    public Health health() {

        long authorsCount = authorRepository.count();
        if (authorsCount > 0) {
            return Health.down()
                    .status(Status.UP)
                    .withDetail("message", "Library is ready!")
                    .build();

        } else {
            return Health.down().withDetail("message", "There must be at least one author!").build();
        }
    }
}
