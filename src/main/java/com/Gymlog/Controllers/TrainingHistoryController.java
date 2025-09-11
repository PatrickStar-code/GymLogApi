package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.TrainingHistoryMapper;
import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Controllers.Response.TrainingHistoryResponse;
import com.Gymlog.Controllers.SwaggerInterface.TrainingHistoryInterface;
import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Service.TrainingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RestController
@RequestMapping("/GymLog/trainingHistory")
@RequiredArgsConstructor
public class TrainingHistoryController implements TrainingHistoryInterface {

 private  final TrainingHistoryService trainingHistoryService ;

    @Override
    public ResponseEntity<TrainingHistoryResponse> saveTrainingHistory(TrainingHistoryRequest trainingHistory, UriComponentsBuilder uriBuilder) {
        TrainingHistoryEntity trainingHistoryEntity = trainingHistoryService.saveTrainingHistory(trainingHistory);
        TrainingHistoryResponse trainingHistoryResponse = TrainingHistoryMapper.toTrainingHistoryResponse(trainingHistoryEntity);
        return ResponseEntity.created(uriBuilder.path("/Gymlog/trainingHistory/{id}").buildAndExpand(trainingHistoryResponse.id()).toUri()).body(trainingHistoryResponse);
    }

    @Override
    public ResponseEntity<Page<TrainingHistoryResponse>> getTrainingHistory(int page, int size) {
        if(page>=0 && size>0) {
            Page<TrainingHistoryEntity> trainingHistory = trainingHistoryService.getAllTrainingHistoryByPage(page, size);
            Page<TrainingHistoryResponse> trainingHistoryResponse = trainingHistory.map(TrainingHistoryMapper::toTrainingHistoryResponse);
            return ResponseEntity.ok().body(trainingHistoryResponse);
        }
        List<TrainingHistoryEntity> trainingHistoryEntity = trainingHistoryService.getAllTrainingHistory();
        Stream<TrainingHistoryResponse> trainingHistoryResponse = trainingHistoryEntity.stream().map(TrainingHistoryMapper::toTrainingHistoryResponse);
        PageImpl<TrainingHistoryResponse> trainingHistoryResponsePage = new PageImpl<>(trainingHistoryResponse.toList());
        return ResponseEntity.ok().body(trainingHistoryResponsePage);
    }

    @Override
    public ResponseEntity<TrainingHistoryResponse> getTrainingHistoryById(Long id) {
        TrainingHistoryEntity trainingHistoryEntity = trainingHistoryService.getTrainingHistoryById(id);
        TrainingHistoryResponse trainingHistoryResponse = TrainingHistoryMapper.toTrainingHistoryResponse(trainingHistoryEntity);
        return ResponseEntity.ok().body(trainingHistoryResponse);
    }

    @Override
    public ResponseEntity<Void> deleteTrainingHistory(Long id) {
        trainingHistoryService.deleteTrainingHistory(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TrainingHistoryResponse> updateTrainingHistory(Long id, TrainingHistoryRequest trainingHistoryRequest) {
        Optional<TrainingHistoryEntity> trainingHistoryEntity = trainingHistoryService.updateTrainingHistory(id, trainingHistoryRequest);
        if(trainingHistoryEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TrainingHistoryEntity trainingHistoryEntity1 = trainingHistoryEntity.get();
        TrainingHistoryResponse trainingHistoryResponse = TrainingHistoryMapper.toTrainingHistoryResponse(trainingHistoryEntity1);
        return ResponseEntity.ok().body(trainingHistoryResponse);
    }



    @Override
    public ResponseEntity<Page<TrainingHistoryResponse>> getTrainingHistoryByUser(Long id, int page, int size) {
        if(page>=0 && size>0) {
            Page<TrainingHistoryEntity> trainingHistory = trainingHistoryService.getAllTrainingHistoryByUserPage(page, size, id);
            Page<TrainingHistoryResponse> trainingHistoryResponse = trainingHistory.map(TrainingHistoryMapper::toTrainingHistoryResponse);
            return ResponseEntity.ok().body(trainingHistoryResponse);
        }
        List<TrainingHistoryEntity> trainingHistoryEntity = trainingHistoryService.getAllTrainingHistoryByUser(id);
        Stream<TrainingHistoryResponse> trainingHistoryResponse = trainingHistoryEntity.stream().map(TrainingHistoryMapper::toTrainingHistoryResponse);
        PageImpl<TrainingHistoryResponse> trainingHistoryResponsePage = new PageImpl<>(trainingHistoryResponse.toList());
        return ResponseEntity.ok().body(trainingHistoryResponsePage);
    }


}
