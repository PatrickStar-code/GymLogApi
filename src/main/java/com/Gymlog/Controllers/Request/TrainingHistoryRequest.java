package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TrainingHistoryRequest(
        @Schema(description = "Id do treino", example = "1")
        Long id,

        @Schema(description = "Id do usuário", example = "1")
        Long user,

        @Schema(description = "Id do plano de treino", example = "1")
        Long workoutPlan,

        @Schema(description = "Data da ocorrencia", example = "2023-06-22T15:52:59.532241")
        LocalDateTime ocurrenceDate
) {
}
