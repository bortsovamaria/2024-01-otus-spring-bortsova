package ru.otus.spring.homework15.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OrderType {

    DRINK("drink"),

    BURGER("burger"),

    FRIES("fries"),

    WATER("water");

    private final String typeName;

    OrderType(String typeName) {
        this.typeName = typeName;
    }

    public static OrderType getFromTypeName(String typeName) {
        for (OrderType type : OrderType.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return OrderType.WATER;
    }
}