package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.FoodRequest;
import com.Gymlog.Controllers.Response.FoodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Alimentos", description = "Operações relacionadas aos alimentos")
public interface FoodControllerInterface {

    @Operation(
            summary = "Listar alimentos (lista simples)",
            description = "Retorna uma lista de alimentos de forma paginada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alimentos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodResponse.class)))
    })
    @GetMapping("/")
    ResponseEntity<?> getFoods(
            @Parameter(description = "Número da página", example = "0") @RequestParam(required = false,defaultValue = "0") int page,
            @Parameter(description = "Quantidade de itens por página", example = "10") @RequestParam(required = false,defaultValue = "0") int size
    );

    @Operation(
            summary = "Buscar alimento por ID",
            description = "Retorna os dados de um alimento específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alimento encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodResponse.class))),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<FoodResponse> getFoodById(
            @Parameter(description = "ID do alimento", example = "1") @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar alimento",
            description = "Atualiza os dados de um alimento existente no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alimento atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodResponse.class))),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<FoodResponse> updateFood(
            @RequestBody
            @Parameter(description = "Dados atualizados do alimento")
            FoodRequest foodRequest,
            @Parameter(description = "ID do alimento a ser atualizado", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Cadastrar novo alimento",
            description = "Salva um novo alimento no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alimento criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PostMapping("/")
    ResponseEntity<FoodResponse> saveFood(
            @RequestBody
            @Parameter(description = "Dados do novo alimento")
            FoodRequest foodRequest,
            @NotNull UriComponentsBuilder uriBuilder
    );



    @Operation(
            summary = "Deletar alimento",
            description = "Remove um alimento do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alimento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @Parameter(description = "ID do alimento", example = "1")
            @PathVariable long id
    );
}
