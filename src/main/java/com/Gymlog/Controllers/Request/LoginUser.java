package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Payload para realizar login do usuário")
public record LoginUser(

        @Schema(description = "Email do usuário", example = "usuario@email.com")
        @Email
        @NotBlank
        String email,

        @Schema(description = "Senha do usuário", example = "123456")
        @NotBlank
        String password

) {
}

