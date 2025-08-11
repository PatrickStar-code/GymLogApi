package com.Gymlog.Enums;


public enum StatusEnum {
    COMPLETED("completed"),
    IN_PROGRESS("in_progress"),
    CANCELLED("cancelled");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

}
