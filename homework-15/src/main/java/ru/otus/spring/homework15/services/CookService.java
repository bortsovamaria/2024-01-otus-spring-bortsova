package ru.otus.spring.homework15.services;


import ru.otus.spring.homework15.models.CookResult;
import ru.otus.spring.homework15.models.Order;

public interface CookService {
    CookResult cooking(Order order);
}