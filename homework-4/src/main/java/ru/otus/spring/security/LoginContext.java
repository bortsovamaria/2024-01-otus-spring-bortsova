package ru.otus.spring.security;

public interface LoginContext {
    void login(String userName);

    boolean isUserLoggedIn();
}
