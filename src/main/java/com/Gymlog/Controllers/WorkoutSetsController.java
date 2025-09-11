package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.FoodMapper;
import com.Gymlog.Controllers.Mapper.WorkoutSetsMapper;
import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.FoodResponse;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Controllers.SwaggerInterface.WorkoutSetsControllerInterface;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import com.Gymlog.Service.WorkoutSetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/GymLog/workoutSets")
@RequiredArgsConstructor
public class WorkoutSetsController implements WorkoutSetsControllerInterface {
    private final WorkoutSetsService service;


    @Override
    public ResponseEntity<Page<WorkoutSetsResponse>> getWorkoutSets(int page, int size) {
        if(page >= 0 && size > 0) {
            Page<WorkoutSetsEntity> workoutSets = service.getAllWorkoutSetsByPage(page, size);
            Page<WorkoutSetsResponse> workoutSetsResponses =  workoutSets.map(WorkoutSetsMapper::toWorkoutSetsResponse);
            return ResponseEntity.ok().body(workoutSetsResponses);
        }

        List<WorkoutSetsEntity> workoutSetsEntities = service.getAllWorkoutSets();

        Stream<WorkoutSetsResponse> foodResponseStream = workoutSetsEntities.stream().map(WorkoutSetsMapper::toWorkoutSetsResponse);
        PageImpl<WorkoutSetsResponse> foodResponsePage = new PageImpl<>(foodResponseStream.toList(), PageRequest.of(0,workoutSetsEntities.size() > 0 ? workoutSetsEntities.size() : 1),workoutSetsEntities.size() > 0 ? workoutSetsEntities.size() : 1);
        return ResponseEntity.ok(foodResponsePage);    }

    @Override
    public ResponseEntity<WorkoutSetsResponse> getWorkoutSetsById(Long id) {
        WorkoutSetsEntity workoutSetsEntity = service.getWorkoutSetsById(id);
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(workoutSetsEntity));
    }

    @Override
    public ResponseEntity<Void> deleteWorkoutSets(Long id) {
        service.deleteWorkoutSets(id);
        return ResponseEntity.noContent().build();
    }



    @Override
    public ResponseEntity<WorkoutSetsResponse> updateWorkoutSets(WorkoutSetsRequest workoutSetsRequest, Long id) {
        Optional<WorkoutSetsEntity> result = service.updateWorkoutSets(workoutSetsRequest, id);
        if(result.isPresent()) return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(result.get()));
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WorkoutSetsResponse> createWorkoutSets(WorkoutSetsRequest workoutSetsRequest, UriComponentsBuilder uriBuilder) {
        WorkoutSetsEntity result = service.createWorkoutSets(workoutSetsRequest);
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(result));
    }
}
