package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Sets do Exercício de Treino", description = "Operações relacionadas aos sets de um exercício dentro do treino")
public interface WorkoutSetsControllerInterface {

    @Operation(
            summary = "Listar todos os sets de exercícios",
            description = "Retorna todos os sets cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Sets retornados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutSetsResponse.class)))
    @GetMapping("/")
    ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSets();

    @Operation(
            summary = "Buscar set por ID",
            description = "Retorna os dados de um set específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Set encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutSetsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Set não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<WorkoutSetsResponse> getWorkoutSetsById(
            @Parameter(description = "ID do set", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Deletar set",
            description = "Remove um set de exercício com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Set deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Set não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWorkoutSets(
            @Parameter(description = "ID do set", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Listar sets por exercício de treino",
            description = "Retorna todos os sets associados a um exercício de treino específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sets encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutSetsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Exercício não encontrado")
    })
    @GetMapping("/{id}/workoutExercises")
    ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSetsByWorkoutExercisesId(
            @Parameter(description = "ID do exercício de treino", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar set de exercício",
            description = "Atualiza os dados de um set específico com base no ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Set atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutSetsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Set não encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<WorkoutSetsResponse> updateWorkoutSets(
            @RequestBody
            @Parameter(description = "Dados atualizados do set")
            WorkoutSetsRequest workoutSetsRequest,
            @Parameter(description = "ID do set", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Criar novo set de exercício",
            description = "Cadastra um novo set dentro de um exercício de treino."
    )
    @ApiResponse(responseCode = "201", description = "Set criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutSetsResponse.class)))
    @PostMapping("/")
    ResponseEntity<WorkoutSetsResponse> createWorkoutSets(
            @RequestBody
            @Parameter(description = "Dados do novo set")
            WorkoutSetsRequest workoutSetsRequest,
            UriComponentsBuilder uriBuilder
    );
}
