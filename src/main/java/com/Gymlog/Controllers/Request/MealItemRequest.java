package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição de item da refeição")
public record MealItemRequest(
        @Schema(example = "100.0",description = "Quantidade  do item da refeição")
        double quantity,

        @Schema(example = "1",description = "Id do alimento da refeição")
        Long food,

        @Schema(example = "1",description = "Id da refeição")
        Long meals
) {
}
