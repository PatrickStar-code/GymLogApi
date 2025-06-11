package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutPlanMapper;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Repository.WorkoutPlanRepository;
import com.Gymlog.Service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/workoutPlan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService service;

    @GetMapping("/")
    public ResponseEntity<List<WorkoutPlanResponse>> getWorkoutPlan() {
        List<WorkoutPlanEntity> workoutPlanEntity = service.findAll();
        List<WorkoutPlanResponse> workoutPlanResponses = workoutPlanEntity.stream().map(WorkoutPlanMapper::toWorkoutPlanResponse).toList();
        return ResponseEntity.ok(workoutPlanResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponse> getWorkoutPlanById(@PathVariable Long id) {
        Optional<WorkoutPlanEntity> workoutPlan = service.findById(id);
        if(workoutPlan.isPresent()){
            return ResponseEntity.ok(WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlan.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        service.deleteWorkoutPlan(id);
        return ResponseEntity.ok().build();
    }

}
