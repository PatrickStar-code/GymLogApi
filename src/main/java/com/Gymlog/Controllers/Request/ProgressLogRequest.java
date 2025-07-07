package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ProgressLogRequest(

        @Schema(description = "Data do progresso", example = "2023-06-22T15:52:59.532241")
        LocalDateTime date,

        @Schema(description = "Peso corporal", example = "80.0")
        double weight,

        @Schema(description = "Percentual de gordura corporal", example = "10.0")
        double bodyFat,

        @Schema(description = "Quadril", example = "5.0")
        double hip,

        @Schema(description = "Torax", example = "5.0")
        double chest,

        @Schema(description = "Braço esquerdo", example = "5.0")
        double armsLeft,

        @Schema(description = "Braço direito", example = "5.0")
        double armsRight,

        @Schema(description = "Quadriceps esquerda", example = "5.0")
        double thighLeft,

        @Schema(description = "Quadriceps direita", example = "5.0")
        double thighRight,

        @Schema(description = "Panturilha esquerda", example = "5.0")
        double leftCalf,

        @Schema(description = "Panturilha direita", example = "5.0")
        double rightCalf,

        @Schema(description = "Cintura", example = "5.0")
        double waist,

        @Schema(description = "Comentario", example = "Comentario")
        String comment,

        @Schema(description = "Responsável pelo progresso", example = "PatrickStar")
        String responsible,

        @Schema(description = "ID do usuário", example = "1")
        Long user
) {
}
