package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requisição de comida")
public record FoodRequest(
        @NotBlank(message = "O nome da comida é obrigatório.")
        @Schema(description = "Nome da comida", example = "Arroz", required = true)
        String foodName,

        @NotBlank(message = "As calorias da comida é obrigatória.")
        @Schema(description = "Calorias da comida", example = "100.0", required = true)
        double calories,

        @NotBlank(message = "As proteinas da comida é obrigatória.")
        @Schema(description = "Proteinas da comida", example = "10.0", required = true)
        double proteins,

        @NotBlank(message = "As carbohidratos da comida é obrigatória.")
        @Schema(description = "Carbohidratos da comida", example = "10.0", required = true)
        double carbs,

        @NotBlank(message = "As fibras da comida é obrigatória.")
        @Schema(description = "Fibras da comida", example = "10.0", required = true)
        double fibers,

        @NotBlank(message = "As gorduras da comida é obrigatória.")
        @Schema(description = "Gorduras da comida", example = "10.0", required = true)
        double fats,

        @NotBlank(message = "As gramas da comida é obrigatória.")
        @Schema(description = "Gramas da comida", example = "10.0", required = true)
        double grams
) {
}
