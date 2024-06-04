package ru.otus.spring.homework15.services;

import ru.otus.spring.homework15.models.FinishedDish;
import ru.otus.spring.homework15.models.PrepareDishResult;

public interface ServedService {
    FinishedDish serve(PrepareDishResult res);
}