package com.Gymlog.Controllers.Response;

import com.Gymlog.Enums.MealType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MealsResponse(
    Long id,
    LocalDateTime dateTime,
    MealType mealType,
    int calories,
    int proteins,
    int carbs,
    int fats,
    UserResponse user
) {
}
