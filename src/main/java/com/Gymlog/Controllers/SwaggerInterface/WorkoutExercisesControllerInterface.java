package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.WorkoutExercicesRequest;
import com.Gymlog.Controllers.Response.WorkoutExercisesResponse;
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

@Tag(name = "Exercícios do Treino", description = "Rotas para manipulação dos exercícios de um plano de treino")
public interface WorkoutExercisesControllerInterface {

    @Operation(
            summary = "Listar todos os exercícios de treino",
            description = "Retorna todos os exercícios de treino cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista de exercícios retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutExercisesResponse.class)))
    @GetMapping("/")
    ResponseEntity<List<WorkoutExercisesResponse>> getWorkoutExercises();

    @Operation(
            summary = "Buscar exercício de treino por ID",
            description = "Retorna os dados de um exercício de treino específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercício de treino encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutExercisesResponse.class))),
            @ApiResponse(responseCode = "404", description = "Exercício de treino não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<WorkoutExercisesResponse> getWorkoutExercisesById(
            @Parameter(description = "ID do exercício de treino", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Listar exercícios por plano de treino",
            description = "Retorna todos os exercícios associados a um plano de treino específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercícios encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutExercisesResponse.class))),
            @ApiResponse(responseCode = "404", description = "Plano de treino não encontrado")
    })
    @GetMapping("/{id}/workoutPlan")
    ResponseEntity<List<WorkoutExercisesResponse>> getWorkoutExercisesByWorkoutPlanId(
            @Parameter(description = "ID do plano de treino", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Deletar exercício de treino",
            description = "Remove um exercício de treino com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercício deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Exercício não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWorkoutExercises(
            @Parameter(description = "ID do exercício de treino", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Criar novo exercício de treino",
            description = "Cadastra um novo exercício dentro de um plano de treino."
    )
    @ApiResponse(responseCode = "201", description = "Exercício criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutExercisesResponse.class)))
    @PostMapping("/")
    ResponseEntity<WorkoutExercisesResponse> createWorkoutExercises(
            @RequestBody
            @Parameter(description = "Dados do novo exercício")
            WorkoutExercicesRequest workoutExercicesRequest,
            UriComponentsBuilder uriBuilder
    );

    @Operation(
            summary = "Atualizar exercício de treino",
            description = "Atualiza os dados de um exercício de treino com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercício atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutExercisesResponse.class))),
            @ApiResponse(responseCode = "404", description = "Exercício não encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<WorkoutExercisesResponse> updateWorkoutExercises(
            @Parameter(description = "ID do exercício de treino", example = "1")
            @PathVariable Long id,
            @RequestBody
            @Parameter(description = "Dados atualizados do exercício")
            WorkoutExercicesRequest workoutExercicesRequest
    );


}
