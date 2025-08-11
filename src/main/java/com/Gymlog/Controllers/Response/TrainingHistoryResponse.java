package com.Gymlog.Controllers.Response;

import com.Gymlog.Enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TrainingHistoryResponse(
        @Schema(description = "Id do treino", example = "1")
        Long id,

        @Schema(description = "Data da ocorrencia", example = "2023-06-22T15:52:59.532241")
        LocalDateTime date,

        @Schema(description = "Dados do usuário")
        UserResponse user ,

        @Schema(description = "Dados do plano de treino")
        WorkoutPlanResponse workoutPlan,

        @Schema(description = "Status", example = "COMPLETED")
        StatusEnum statusEnum,

        @Schema(description = "Comentario", example = "Comentario")
        String comment
) {
}
