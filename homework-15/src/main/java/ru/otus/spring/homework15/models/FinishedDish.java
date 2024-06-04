package ru.otus.spring.homework15.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinishedDish {

    private PrepareDishResult result;

    private boolean isServed;
}
