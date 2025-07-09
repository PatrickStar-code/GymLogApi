package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutPlanMapper;
import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
import com.Gymlog.Controllers.SwaggerInterface.WorkoutPlanControllerInterface;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Repository.WorkoutPlanRepository;
import com.Gymlog.Service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/workoutPlan")
@RequiredArgsConstructor
public class WorkoutPlanController implements WorkoutPlanControllerInterface {

    private final WorkoutPlanService service;


    @Override
    public ResponseEntity<List<WorkoutPlanResponse>> getWorkoutPlan() {
        List<WorkoutPlanEntity> workoutPlanEntity = service.findAll();
        List<WorkoutPlanResponse> workoutPlanResponses = workoutPlanEntity.stream().map(WorkoutPlanMapper::toWorkoutPlanResponse).toList();
        return ResponseEntity.ok(workoutPlanResponses);
    }

    @Override
    public ResponseEntity<WorkoutPlanResponse> getWorkoutPlanById(Long id) {
        Optional<WorkoutPlanEntity> workoutPlan = service.findById(id);
        if(workoutPlan.isPresent()){
            return ResponseEntity.ok(WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlan.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WorkoutPlanResponse> updateWorkoutPlan(Long id, WorkoutPlanRequest workoutPlanRequest) {
        Optional<WorkoutPlanEntity> workoutPlan = service.updateWorkoutPlan(id, workoutPlanRequest);
        if(workoutPlan.isPresent()){
            return ResponseEntity.ok(WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlan.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<WorkoutPlanResponse>> getWorkoutPlanByUser(Long id) {
        List<WorkoutPlanEntity> workoutPlanEntity = service.findByUser(id);
        List<WorkoutPlanResponse> workoutPlanResponses = workoutPlanEntity.stream().map(WorkoutPlanMapper::toWorkoutPlanResponse).toList();
        return ResponseEntity.ok(workoutPlanResponses);
    }

    @Override
    public ResponseEntity<WorkoutPlanResponse> createWorkoutPlan(WorkoutPlanRequest workoutPlanRequest, UriComponentsBuilder uriBuilder) {
        WorkoutPlanEntity workoutPlanEntity = service.createWorkoutPlan(workoutPlanRequest);
        WorkoutPlanResponse workoutPlanResponse = WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlanEntity);
        var uri = uriBuilder.path("/GymLog/workoutPlan/{id}").buildAndExpand(workoutPlanResponse.id()).toUri();
        return ResponseEntity.created(uri).body(workoutPlanResponse);
    }

    @Override
    public ResponseEntity<Void> deleteWorkoutPlan(Long id) {
        service.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }
}
