package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutSetsMapper;
import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Controllers.SwaggerInterface.WorkoutSetsControllerInterface;
import com.Gymlog.Entity.WorkoutSetsEntity;
import com.Gymlog.Service.WorkoutSetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/workoutSets")
@RequiredArgsConstructor
public class WorkoutSetsController implements WorkoutSetsControllerInterface {

    private final WorkoutSetsService service;



    @Override
    public ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSets() {
        List<WorkoutSetsEntity> workoutSets = service.getAllWorkoutSets();
        List<WorkoutSetsResponse> workoutSetsResponse = workoutSets.stream().map(WorkoutSetsMapper::toWorkoutSetsResponse).toList();
        return ResponseEntity.ok(workoutSetsResponse);
    }

    @Override
    public ResponseEntity<WorkoutSetsResponse> getWorkoutSetsById(Long id) {
        Optional<WorkoutSetsEntity> workoutSets = service.getWorkoutSetsById(id);
        if(workoutSets.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(workoutSets.get()));
    }

    @Override
    public ResponseEntity<Void> deleteWorkoutSets(Long id) {
        Optional<WorkoutSetsEntity> workoutSets = service.getWorkoutSetsById(id);
        if(workoutSets.isEmpty()) return ResponseEntity.notFound().build();
        service.deleteWorkoutSets(id);
        return ResponseEntity.noContent().build();    }

    @Override
    public ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSetsByWorkoutExercisesId(Long id) {
        List<WorkoutSetsEntity> workoutSets = service.findByWorkoutExercicesId(id);
        List<WorkoutSetsResponse> response =  workoutSets.stream().map(WorkoutSetsMapper::toWorkoutSetsResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WorkoutSetsResponse> updateWorkoutSets(WorkoutSetsRequest workoutSetsRequest, Long id) {
        Optional<WorkoutSetsEntity> response = service.updateWorkoutSets(workoutSetsRequest, id);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(response.get()));
    }

    @Override
    public ResponseEntity<WorkoutSetsResponse> createWorkoutSets(WorkoutSetsRequest workoutSetsRequest, UriComponentsBuilder uriBuilder) {
        Optional<WorkoutSetsEntity> response = service.createWorkoutSets(workoutSetsRequest);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        var uri = uriBuilder.path("/GymLog/workoutSets/{id}").buildAndExpand(response.get().getId()).toUri();
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(response.get()));
    }
}
