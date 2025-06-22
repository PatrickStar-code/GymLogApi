package com.Gymlog.Controllers.Response;

import com.Gymlog.Enums.ActivyLevel;
import com.Gymlog.Enums.GenderEnum;
import com.Gymlog.Enums.Goal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serial;
import java.time.LocalDateTime;

@Builder
@Schema(description = "Resposta contendo os dados do usuário")
public record UserResponse(

        @Schema(description = "ID do usuário", example = "1")
        Long userId,

        @Schema(description = "Nome de usuário", example = "PatrickStar")
        String username,

        @Schema(description = "E-mail do usuário", example = "teste@gmail.com")
        String email,

        @Schema(description = "Data de criação do cadastro", example = "2025-06-22T15:52:59.532241")
        LocalDateTime createdAt,

        @Schema(description = "Gênero do usuário", example = "MALE")
        GenderEnum gender,

        @Schema(description = "Altura do usuário em centímetros", example = "175.0")
        double height,

        @Schema(description = "Peso atual do usuário em kg", example = "80.0")
        double weight,

        @Schema(description = "Idade do usuário", example = "30")
        int age,

        @Schema(description = "Objetivo do usuário", example = "MAINTAIN_WEIGHT")
        Goal goal,

        @Schema(description = "Peso alvo do usuário em kg", example = "100.0")
        double goalWeight,

        @Schema(description = "Data de nascimento do usuário", example = "1995-04-20T00:00:00")
        LocalDateTime birthDate,

        @Schema(description = "Nível de atividade física", example = "LIGHTLY_ACTIVE")
        ActivyLevel activyLevel,

        @Schema(description = "URL do avatar do usuário", example = "https://example.com/avatar.png")
        String avatarUrl,

        @Schema(description = "Indica se o usuário está ativo", example = "true")
        boolean isActive,

        @Schema(description = "Data da última atualização do usuário", example = "2025-06-22T15:59:30.145856")
        LocalDateTime updatedAt

) {
}
