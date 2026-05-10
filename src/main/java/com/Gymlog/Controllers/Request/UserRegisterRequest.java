package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "Requisição para registro inicial de um usuário.")
public record UserRegisterRequest(
        @NotBlank(message = "O nome de usuário é obrigatório.")
        @Schema(description = "Nome de usuário", example = "joaosilva", required = true)
        String username,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        @Schema(description = "E-mail do usuário", example = "joao@email.com", required = true)
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Schema(description = "Senha do usuário (mínimo 6 caracteres)", example = "senhaSegura123", required = true)
        String password
) {
}
