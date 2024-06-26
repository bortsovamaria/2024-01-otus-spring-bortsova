package ru.otus.spring.homework12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework12.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
