package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
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

@Tag(name = "Plano de Treino", description = "Rotas para manipulação de planos de treino")
public interface WorkoutPlanControllerInterface {

    @Operation(
            summary = "Listar todos os planos de treino",
            description = "Retorna todos os planos de treino cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Planos de treino retornados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutPlanResponse.class)))
    @GetMapping("/")
    ResponseEntity<List<WorkoutPlanResponse>> getWorkoutPlan();

    @Operation(
            summary = "Buscar plano de treino por ID",
            description = "Retorna os dados de um plano de treino específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de treino encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutPlanResponse.class))),
            @ApiResponse(responseCode = "404", description = "Plano de treino não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<WorkoutPlanResponse> getWorkoutPlanById(
            @Parameter(description = "ID do plano de treino", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar plano de treino",
            description = "Atualiza os dados de um plano de treino com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de treino atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutPlanResponse.class))),
            @ApiResponse(responseCode = "404", description = "Plano de treino não encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<WorkoutPlanResponse> updateWorkoutPlan(
            @Parameter(description = "ID do plano de treino", example = "1")
            @PathVariable Long id,
            @RequestBody
            @Parameter(description = "Dados atualizados do plano de treino")
            WorkoutPlanRequest workoutPlanRequest
    );

    @Operation(
            summary = "Buscar planos de treino por ID de usuário",
            description = "Retorna todos os planos de treino associados a um usuário específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planos de treino encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutPlanResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou sem planos")
    })
    @GetMapping("/{id}/user")
    ResponseEntity<List<WorkoutPlanResponse>> getWorkoutPlanByUser(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Criar novo plano de treino",
            description = "Cadastra um novo plano de treino no sistema."
    )
    @ApiResponse(responseCode = "201", description = "Plano de treino criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkoutPlanResponse.class)))
    @PostMapping("/")
    ResponseEntity<WorkoutPlanResponse> createWorkoutPlan(
            @RequestBody
            @Parameter(description = "Dados do novo plano de treino")
            WorkoutPlanRequest workoutPlanRequest,
            UriComponentsBuilder uriBuilder
    );

    @Operation(
            summary = "Deletar plano de treino",
            description = "Remove permanentemente um plano de treino com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Plano de treino deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano de treino não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWorkoutPlan(
            @Parameter(description = "ID do plano de treino", example = "1")
            @PathVariable Long id
    );
}
