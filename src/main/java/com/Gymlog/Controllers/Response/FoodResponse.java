package com.Gymlog.Controllers.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record FoodResponse(
        @Schema(description = "Id da comida", example = "1")
        Long id,

        @Schema(description = "Nome da comida", example = "Arroz")
        String foodName,

        @Schema(description = "Calorias da comida", example = "100.0")
        double calories,

        @Schema(description = "Proteinas da comida", example = "10.0")
        double proteins,

        @Schema(description = "Carbohidratos da comida", example = "10.0")
        double carbs,

        @Schema(description = "Fibras da comida", example = "10.0")
        double fibers,

        @Schema(description = "Gorduras da comida", example = "10.0")
        double fats,

        @Schema(description = "Gramas da comida", example = "10.0")
        double grams
) {
}
