package com.Gymlog.Controllers.Request;

import com.Gymlog.Enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TrainingHistoryRequest(

        @Schema(description = "Id do usuário", example = "1")
        Long userId,

        @Schema(description = "Id do plano de treino", example = "1")
        Long workoutPlanId,

        @Schema(description = "Data da ocorrencia", example = "2023-06-22T15:52:59.532241")
        LocalDateTime ocurrenceDate,

        @Schema(description = "Status", example = "COMPLETED")
        StatusEnum statusEnum,

        @Schema(description = "Comentario", example = "Comentario")
        String comment
) {
}
