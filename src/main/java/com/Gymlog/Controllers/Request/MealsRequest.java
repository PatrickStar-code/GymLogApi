package com.Gymlog.Controllers.Request;

import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Enums.MealType;

import java.time.LocalDateTime;

public record MealsRequest(
        LocalDateTime dateTime,
        MealType mealType,
        int calories,
        int proteins,
        int carbs,
        int fats,
        Long user
) {
}
