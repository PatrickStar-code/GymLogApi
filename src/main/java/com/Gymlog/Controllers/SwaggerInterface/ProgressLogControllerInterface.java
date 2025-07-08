package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.ProgressLogRequest;
import com.Gymlog.Controllers.Response.ProgressLogResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Progresso", description = "Operações relacionadas ao progresso do usuário")
public interface ProgressLogControllerInterface {

    @Operation(
            summary = "Buscar progresso por ID",
            description = "Retorna um registro de progresso específico com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de progresso encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgressLogResponse.class))),
            @ApiResponse(responseCode = "404", description = "Registro de progresso não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<ProgressLogResponse> getProgressLogById(
            @Parameter(description = "ID do progresso", example = "1") @PathVariable Long id
    );

    @Operation(
            summary = "Criar novo registro de progresso",
            description = "Salva um novo registro de progresso para o usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro de progresso criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgressLogResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para criação", content = @Content)
    })
    @PostMapping("/")
    ResponseEntity<ProgressLogResponse> createProgressLog(
            @RequestBody
            @Parameter(description = "Dados do novo progresso") ProgressLogRequest progressLogRequest,
            @NotNull UriComponentsBuilder uriBuilder
    );

    @Operation(
            summary = "Atualizar registro de progresso",
            description = "Atualiza os dados de um registro de progresso existente com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de progresso atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgressLogResponse.class))),
            @ApiResponse(responseCode = "404", description = "Registro de progresso não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<ProgressLogResponse> updateProgressLog(
            @RequestBody
            @Parameter(description = "Dados atualizados do progresso") ProgressLogRequest progressLogRequest,
            @Parameter(description = "ID do progresso", example = "1") @PathVariable Long id
    );

    @Operation(
            summary = "Deletar registro de progresso",
            description = "Remove um registro de progresso com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro de progresso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro de progresso não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProgressLog(
            @Parameter(description = "ID do progresso", example = "1") @PathVariable Long id
    );

    @Operation(
            summary = "Listar registros de progresso por usuário",
            description = "Retorna todos os registros de progresso vinculados a um usuário específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de registros de progresso retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgressLogResponse.class)))
    })
    @GetMapping("/{id}/user")
    ResponseEntity<List<ProgressLogResponse>> getAllProgressLogByUser(
            @Parameter(description = "ID do usuário", example = "1") @PathVariable Long id
    );
}
