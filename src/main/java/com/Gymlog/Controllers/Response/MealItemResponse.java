package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record MealItemResponse(
    Long id,
    int quantity,
    FoodResponse food,
    MealsResponse meals
) {
}
