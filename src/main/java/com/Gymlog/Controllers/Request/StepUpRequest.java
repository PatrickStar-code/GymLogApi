package com.Gymlog.Controllers.Request;

import com.Gymlog.Enums.GenderEnum;
import com.Gymlog.Enums.Goal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Requisição para finalização do perfil (Step-Up).")
public record StepUpRequest(
        @NotNull(message = "O gênero é obrigatório.")
        @Schema(description = "Gênero do usuário", example = "MALE", required = true)
        GenderEnum gender,

        @Schema(description = "Altura do usuário em metros", example = "1.75", required = true)
        double height,

        @Schema(description = "Peso atual do usuário em kg", example = "70.5", required = true)
        double weight,

        @NotNull(message = "O objetivo é obrigatório.")
        @Schema(description = "Objetivo do usuário", example = "GAIN_MUSCLE", required = true)
        Goal goal,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Schema(description = "Data de nascimento do usuário", example = "1999-05-15T00:00:00", required = true)
        LocalDateTime birthdate
) {
}
