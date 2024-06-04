package ru.otus.spring.homework15.services;


import ru.otus.spring.homework15.models.CookResult;
import ru.otus.spring.homework15.models.PrepareDishResult;

public interface PrepareDishService {
    PrepareDishResult prepare(CookResult result);
}