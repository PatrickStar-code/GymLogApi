package com.Gymlog.Controllers.Request;

public record MealItemRequest(
        double quantity,
        Long food,
        Long meals
) {
}
