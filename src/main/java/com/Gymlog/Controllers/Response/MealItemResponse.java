package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record MealItemResponse(
    Long id,
    double quantity,
    FoodResponse food,
    MealsResponse meals
) {
}
