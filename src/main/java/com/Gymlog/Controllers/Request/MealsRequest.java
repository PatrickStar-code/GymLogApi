package com.Gymlog.Controllers.Request;

import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Enums.MealType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MealsRequest(

        @Schema(description = "Data e hora da refeição", example = "2025-06-22T15:52:59.532241")
        LocalDateTime dateTime,

        @Schema(description = "Tipo da refeição", example = "BREAKFAST")
        MealType mealType,

        @Schema(description = "Calorias da refeição", example = "100.0")
        double calories,

        @Schema(description = "Proteinas da refeição", example = "10.0")
        double proteins,

        @Schema(description = "Carbohidratos da refeição", example = "10.0")
        double carbs,

        @Schema(description = "Fibras da refeição", example = "10.0")
        double fibers,

        @Schema(description = "Gorduras da refeição", example = "10.0")
        double fats,

        @Schema(description = "Id do usuário", example = "1")
        Long user
) {
}
