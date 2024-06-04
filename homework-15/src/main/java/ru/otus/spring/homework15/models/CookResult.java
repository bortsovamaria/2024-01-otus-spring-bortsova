package ru.otus.spring.homework15.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CookResult {

    private OrderType orderType;

    private int count;

    private int tableNum;
}