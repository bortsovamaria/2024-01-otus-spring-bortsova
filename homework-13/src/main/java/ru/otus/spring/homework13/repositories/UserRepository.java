package ru.otus.spring.homework13.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework13.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
