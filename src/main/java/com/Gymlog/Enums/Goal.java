package com.Gymlog.Enums;

public enum Goal {
    LOSE_WEIGHT("lose_weight"),
    MAINTAIN_WEIGHT("maintain_weight"),
    GAIN_WEIGHT("gain_weight");

    private final String value;

    Goal(String value) {
        this.value = value;
    }
}
