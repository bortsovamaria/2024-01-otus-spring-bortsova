package ru.otus.spring.homework15.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Order {

    private String type;

    private int tableNum;

    private int count;
}