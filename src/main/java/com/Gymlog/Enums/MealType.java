package com.Gymlog.Enums;

public enum MealType {
    COFFE("coffee"),
    BREAKFAST("breakfast"),
    LUNCH("lunch"),
    DINNER("dinner");

    private final String value;

    MealType(String value) {
        this.value = value;
    }
}
