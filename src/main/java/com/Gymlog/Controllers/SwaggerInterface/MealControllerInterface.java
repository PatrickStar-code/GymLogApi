package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.MealsRequest;
import com.Gymlog.Controllers.Response.MealsResponse;
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

@Tag(name = "Refeições", description = "Operações relacionadas às refeições do usuário")
public interface MealControllerInterface {

    @Operation(
            summary = "Listar todas as refeições",
            description = "Retorna todas as refeições cadastradas no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista de refeições retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MealsResponse.class)))
    @GetMapping("/")
    ResponseEntity<List<MealsResponse>> getMeals();

    @Operation(
            summary = "Buscar refeição por ID",
            description = "Retorna os dados de uma refeição específica com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Refeição encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MealsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Refeição não encontrada")
    })
    @GetMapping("/{id}")
    ResponseEntity<MealsResponse> getMealsById(
            @Parameter(description = "ID da refeição", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Deletar refeição",
            description = "Remove uma refeição com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Refeição deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Refeição não encontrada")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @Parameter(description = "ID da refeição", example = "1")
            @PathVariable long id
    );

    @Operation(
            summary = "Criar nova refeição",
            description = "Cadastra uma nova refeição no sistema."
    )
    @ApiResponse(responseCode = "201", description = "Refeição criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MealsResponse.class)))
    @PostMapping("/")
    ResponseEntity<MealsResponse> createMeals(
            @RequestBody
            @Parameter(description = "Dados da nova refeição")
            MealsRequest mealsRequest,
            UriComponentsBuilder uriBuilder
    );

    @Operation(
            summary = "Atualizar refeição",
            description = "Atualiza os dados de uma refeição existente com base no ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Refeição atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MealsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Refeição não encontrada")
    })
    @PutMapping("/{id}")
    ResponseEntity<MealsResponse> updateMeals(
            @RequestBody
            @Parameter(description = "Dados atualizados da refeição")
            MealsRequest mealsRequest,
            @Parameter(description = "ID da refeição", example = "1")
            @PathVariable Long id
    );
}
