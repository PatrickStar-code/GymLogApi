package com.Gymlog.Controllers.Request;

import com.Gymlog.Enums.ActivyLevel;
import com.Gymlog.Enums.GenderEnum;
import com.Gymlog.Enums.Goal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(description = "Requisição para criação ou atualização de um usuário.")

public record UserRequest(
        @NotBlank(message = "O nome de usuário é obrigatório.")
        @Schema(description = "Nome de usuário", example = "joaosilva", required = true)
        String username,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        @Schema(description = "E-mail do usuário", example = "joao@email.com", required = true)
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Schema(description = "Senha do usuário (mínimo 6 caracteres)", example = "senhaSegura123", required = true)
        String password,

        @Schema(description = "Data de criação do usuário (gerenciada pelo sistema)", example = "2024-06-21T10:00:00")
        LocalDateTime createdAt,

        @NotNull(message = "O gênero é obrigatório.")
        @Schema(description = "Gênero do usuário", example = "MALE", required = true)
        GenderEnum gender,

        @Schema(description = "Altura do usuário em metros", example = "1.75", required = true)
        double height,

        @Schema(description = "Peso atual do usuário em kg", example = "70.5", required = true)
        double weight,

        @Positive(message = "A idade deve ser um número positivo.")
        @Min(value = 1, message = "A idade deve ser maior que 0.")
        @Schema(description = "Idade do usuário", example = "25", required = true)
        int age,

        @NotNull(message = "O objetivo é obrigatório.")
        @Schema(description = "Objetivo do usuário", example = "GAIN_MUSCLE", required = true)
        Goal goal,

        @Positive(message = "O peso alvo deve ser um número positivo.")
        @Schema(description = "Peso alvo do usuário em kg", example = "75.0", required = true)
        double goalWeight,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Schema(description = "Data de nascimento do usuário", example = "1999-05-15T00:00:00", required = true)
        LocalDateTime birthDate,

        @NotNull(message = "O nível de atividade é obrigatório.")
        @Schema(description = "Nível de atividade física do usuário", example = "HIGH", required = true)
        ActivyLevel activyLevel,

        @NotBlank(message = "A URL do avatar é obrigatória.")
        @Schema(description = "URL do avatar do usuário", example = "https://example.com/avatar.png", required = true)
        String avatarUrl,

        @Schema(description = "Indica se o usuário está ativo", example = "true")
        boolean isActive,

        @Schema(description = "Data da última atualização do usuário (gerenciada pelo sistema)", example = "2024-06-21T12:00:00")
        LocalDateTime updatedAt
) {
}
