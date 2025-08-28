package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.TrainingHistoryMapper;
import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Controllers.Response.TrainingHistoryResponse;
import com.Gymlog.Controllers.SwaggerInterface.TrainingHistoryInterface;
import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Service.TrainingHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RestController
@RequestMapping("/GymLog/trainingHistory")
@RequiredArgsConstructor
public class TrainingHistoryController implements TrainingHistoryInterface {

 private  final TrainingHistoryService trainingHistoryService ;

    @Override
    public ResponseEntity<TrainingHistoryResponse> saveTrainingHistory(TrainingHistoryRequest trainingHistory, UriComponentsBuilder uriBuilder) {
        System.out.println("trainingHistory = " + trainingHistory);
        TrainingHistoryEntity trainingHistoryEntity = trainingHistoryService.saveTrainingHistory(trainingHistory);
        TrainingHistoryResponse trainingHistoryResponse = TrainingHistoryMapper.toTrainingHistoryResponse(trainingHistoryEntity);
        return ResponseEntity.created(uriBuilder.path("/Gymlog/trainingHistory/{id}").buildAndExpand(trainingHistoryResponse.id()).toUri()).body(trainingHistoryResponse);
    }
}
