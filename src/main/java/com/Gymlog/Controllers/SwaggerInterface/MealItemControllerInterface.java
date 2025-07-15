package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.MealItemRequest;
import com.Gymlog.Controllers.Response.MealItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Item da refeição")
public interface MealItemControllerInterface {

    @Operation(
            summary = "Listar todos os itens de refeição",
            description = "Retorna uma lista com todos os itens de refeição cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MealItemResponse.class)))
    @GetMapping("/")
    ResponseEntity<Page<MealItemResponse>> getMealItem(@Parameter(description = "Número da página", example = "0") @RequestParam(required = false,defaultValue = "0") int page,
                                                       @Parameter(description = "Quantidade de itens por página", example = "10") @RequestParam(required = false,defaultValue = "0") int size);

    @Operation(
            summary = "Buscar item de refeição por ID",
            description = "Retorna os dados de um item de refeição específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de refeição encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MealItemResponse.class))),
            @ApiResponse(responseCode = "404", description = "Item de refeição não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<MealItemResponse> getMealItemById(
            @Parameter(description = "ID do item de refeição", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar item de refeição",
            description = "Atualiza os dados de um item de refeição existente no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MealItemResponse.class))),
            @ApiResponse(responseCode = "404", description = "Item de refeição não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<MealItemResponse> updateMealItem(
            @RequestBody
            @Parameter(description = "Dados atualizados do item de refeição")
            MealItemRequest mealItemRequest,
            @Parameter(description = "ID do item de refeição", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Criar novo item de refeição",
            description = "Cadastra um novo item de refeição no sistema."
    )
    @ApiResponse(responseCode = "201", description = "Item criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MealItemResponse.class)))
    @PostMapping("/")
    ResponseEntity<MealItemResponse> createMealItem(
            @RequestBody
            @Parameter(description = "Dados do novo item de refeição")
            MealItemRequest mealItemRequest,
            UriComponentsBuilder uriBuilder
    );

    @Operation(
            summary = "Deletar item de refeição",
            description = "Remove um item de refeição do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item de refeição não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMealItem(
            @Parameter(description = "ID do item de refeição", example = "1")
            @PathVariable Long id
    );
}
