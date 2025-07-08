package com.Gymlog.Controllers.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Informações do item da refeição")
public record MealItemResponse(
        @Schema(example = "1",description = "Id do item da refeição")
    Long id,

    @Schema(example = "100.0",description = "Quantidade  do item da refeição")
    double quantity,

    @Schema(description = "Informações da comida")
    FoodResponse food,

    @Schema(description = "Informações da refeição")
    MealsResponse meals
) {
}
