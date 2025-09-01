package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Controllers.Response.TrainingHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

public interface TrainingHistoryInterface {
    @Operation(
            summary = "Cadastrar novo historico de treino",
            description = "Salva um novo historico de treino no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Historico de treino criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<TrainingHistoryResponse> saveTrainingHistory(@RequestBody TrainingHistoryRequest trainingHistory, UriComponentsBuilder uriBuilder);

    @Operation(
            summary = "Buscar historico de treino",
            description = "Busca um historico de treino no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historico de treino encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Page<TrainingHistoryResponse>> getTrainingHistory(@Parameter(description = "Número da página", example = "0") @RequestParam(required = false,defaultValue = "0") int page,
                                                                            @Parameter(description = "Quantidade de itens por página", example = "10") @RequestParam(required = false,defaultValue = "0") int size);


    @Operation(
            summary = "Buscar historico de treino por id",
            description = "Busca um historico de treino no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historico de treino encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainingHistoryResponse> getTrainingHistoryById(@Parameter(description = "ID do historico de treino", example = "1") @PathVariable Long id);

    @Operation(
            summary = "Deletar historico de treino",
            description = "Deleta um historico de treino no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historico de treino deletado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingHistory(@Parameter(description = "ID do historico de treino", example = "1") @PathVariable Long id);

    @Operation(
            summary = "Atualizar historico de treino",
            description = "Atualiza um historico de treino no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historico de treino atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrainingHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TrainingHistoryResponse> updateTrainingHistory(@Parameter(description = "ID do historico de treino", example = "1") @PathVariable Long id,
                                                                          @RequestBody TrainingHistoryRequest trainingHistoryRequest);
}
