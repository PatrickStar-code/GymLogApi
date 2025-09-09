package com.Gymlog.Controllers.Response;

import com.Gymlog.Enums.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MealsResponse(
        @Schema(description = "Id da refeição", example = "1")
    Long id,

    @Schema(description = "Data e hora da refeição", example = "2025-06-22T15:52:59.532241")
    LocalDateTime dateTime,

    @Schema(description = "Tipo da refeição", example = "BREAKFAST")
    MealType mealType,


    @Schema(description = "Dados do usuário")
    UserResponse user
) {
}
