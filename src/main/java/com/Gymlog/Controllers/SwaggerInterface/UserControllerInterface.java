package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.UpdatePassword;
import com.Gymlog.Controllers.Request.UpdateRequest;
import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Controllers.Response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Usuário", description = "Operações relacionadas aos usuários")
public interface UserControllerInterface {
    @Operation(
            summary = "Buscar Pelo Bearer Code",
            description = "Retorna os dados do Usuario a partir do Bearer Token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/me")
    ResponseEntity<UserResponse> getUserByBearerToken();



    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados do usuário a partir do ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Buscar usuários",
            description = "Retorna uma lista de usuários cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
    })
    @GetMapping("/")
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(
            summary = "Registrar novo usuário",
            description = "Cadastra um novo usuário no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PostMapping("/register")
    ResponseEntity<UserResponse> registerUser(
            @RequestBody @Valid
            @Parameter(description = "Dados para criação do usuário")
            UserRequest userRequest,
            @NotNull UriComponentsBuilder uriBuilder
    );

    @Operation(
            summary = "Ativar ou desativar usuário",
            description = "Atualiza o status de ativação do usuário (ativo/inativo)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do usuário atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}/is-active")
    ResponseEntity<UserResponse> updateIsActive(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar senha do usuário",
            description = "Permite que o usuário altere sua senha atual."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}/password")
    ResponseEntity<UserResponse> updatePassword(
            @RequestBody
            @Parameter(description = "Dados para atualização da senha")
            UpdatePassword updatePassword,
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar dados do usuário",
            description = "Permite alterar informações como nome, email, sexo, etc."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(
            @RequestBody
            @Parameter(description = "Dados para atualização do usuário")
            UpdateRequest userRequest,
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Verificar email do usuário",
            description = "Verifica a conta do usuário utilizando o código enviado por email."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário verificado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Código de verificação inválido ou expirado")
    })
    @GetMapping("/verify-user")
    ResponseEntity<String> verifyEmail(
            @RequestParam(value = "code")
            @Parameter(description = "Código de verificação enviado para o email do usuário", example = "abc123")
            String code
    );

    @Operation(
            summary = "Deletar usuário",
            description = "Remove o usuário permanentemente do sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable long id
    );
}
