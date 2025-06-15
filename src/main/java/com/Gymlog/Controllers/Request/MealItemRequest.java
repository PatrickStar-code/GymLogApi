package com.Gymlog.Controllers.Request;

public record MealItemRequest(
        int quantity,
        Long food,
        Long meals
) {
}
