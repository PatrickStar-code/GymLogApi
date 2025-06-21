package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutSetsMapper;
import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.WorkoutSetsEntity;
import com.Gymlog.Service.WorkoutSetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/workoutSets")
@RequiredArgsConstructor
public class WorkoutSetsController {

    private final WorkoutSetsService service;


    @GetMapping("/")
    public ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSets() {
        List<WorkoutSetsEntity> workoutSets = service.getAllWorkoutSets();
        List<WorkoutSetsResponse> workoutSetsResponse = workoutSets.stream().map(WorkoutSetsMapper::toWorkoutSetsResponse).toList();
        return ResponseEntity.ok(workoutSetsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSetsResponse> getWorkoutSetsById(@PathVariable Long id) {
        Optional<WorkoutSetsEntity> workoutSets = service.getWorkoutSetsById(id);
        if(workoutSets.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(workoutSets.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSets(@PathVariable Long id) {
        Optional<WorkoutSetsEntity> workoutSets = service.getWorkoutSetsById(id);
        if(workoutSets.isEmpty()) return ResponseEntity.notFound().build();
        service.deleteWorkoutSets(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/workoutExercises")
    public ResponseEntity<List<WorkoutSetsResponse>> getWorkoutSetsByWorkoutExercisesId(@PathVariable Long id) {
            List<WorkoutSetsEntity> workoutSets = service.findByWorkoutExercicesId(id);
            List<WorkoutSetsResponse> response =  workoutSets.stream().map(WorkoutSetsMapper::toWorkoutSetsResponse).toList();
            return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSetsResponse> updateWorkoutSets(@RequestBody WorkoutSetsRequest workoutSetsRequest, @PathVariable Long id) {
        Optional<WorkoutSetsEntity> response = service.updateWorkoutSets(workoutSetsRequest, id);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(response.get()));
    }

    @PostMapping("/")
    public ResponseEntity<WorkoutSetsResponse> createWorkoutSets(@RequestBody WorkoutSetsRequest workoutSetsRequest) {
        Optional<WorkoutSetsEntity> response = service.createWorkoutSets(workoutSetsRequest);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WorkoutSetsMapper.toWorkoutSetsResponse(response.get()));
    }
}
