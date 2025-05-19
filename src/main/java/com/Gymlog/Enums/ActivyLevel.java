package com.Gymlog.Enums;

public enum ActivyLevel {
    SEDENTARY("sedentary"),
    LIGHTLY_ACTIVE("lightly_active"),
    ACTIVE("active"),
    VERY_ACTIVE("very_active");

    private final String value;

    ActivyLevel(String value) {
        this.value = value;
    }
}
