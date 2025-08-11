package com.Gymlog.Controllers.SwaggerInterface;

import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Controllers.Response.TrainingHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

}
