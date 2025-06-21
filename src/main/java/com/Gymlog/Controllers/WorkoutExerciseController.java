package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutExercicesMapper;
import com.Gymlog.Controllers.Request.WorkoutExercicesRequest;
import com.Gymlog.Controllers.Response.ExerciseResponse;
import com.Gymlog.Controllers.Response.WorkoutExercisesResponse;
import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Service.ExerciseApiService;
import com.Gymlog.Service.WorkoutExercisesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/workoutExercise")
@RequiredArgsConstructor
public class WorkoutExerciseController {

    private final WorkoutExercisesService service;
    private final ExerciseApiService exerciseApiService;

    @GetMapping("/")
    public ResponseEntity<List<WorkoutExercisesResponse>> getWorkoutExercises() {
        List<WorkoutExercisesEntity> workoutExercisesEntity = service.getWorkoutExercises();

        List<WorkoutExercisesResponse> response = workoutExercisesEntity.stream().map(entity -> {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(entity.getExerciceId());

            if (exerciseResponse == null) {
                return null;
            }


            ExerciseResponse exercise = new ExerciseResponse(
                exerciseResponse.exerciseId(),
                exerciseResponse.name(),
                exerciseResponse.gifUrl(),
                exerciseResponse.instructions(),
                exerciseResponse.targetMuscles(),
                exerciseResponse.bodyParts(),
                exerciseResponse.equipments(),
                exerciseResponse.secondaryMuscles()
            );

            return new WorkoutExercisesResponse(
                    entity.getId(),
                    exercise,
                    entity.getWorkoutPlan().getId()
            );
        }).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutExercisesResponse> getWorkoutExercisesById(@PathVariable Long id) {
        Optional<WorkoutExercisesEntity> workoutExercises = service.findById(id);
        if(workoutExercises.isPresent()) {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(workoutExercises.get().getExerciceId());
            WorkoutExercisesResponse workoutExercisesResponse = WorkoutExercicesMapper.toWorkoutExercisesResponse(workoutExercises.get(), exerciseResponse);
            return ResponseEntity.ok(workoutExercisesResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/workoutPlan")
    public ResponseEntity<List<WorkoutExercisesResponse>> getWorkoutExercisesByWorkoutPlanId(@PathVariable Long id) {
        List<WorkoutExercisesEntity> workoutExercises = service.findByWorkoutPlanId(id);
        List<WorkoutExercisesResponse> response = workoutExercises.stream().map(entity -> {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(entity.getExerciceId());
            return new WorkoutExercisesResponse(
                    entity.getId(),
                    exerciseResponse,
                    entity.getWorkoutPlan().getId()
            );
        }).toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutExercises(@PathVariable Long id) {
        Optional<WorkoutExercisesEntity> result = service.findById(id);
        if(result.isPresent()) {
            service.deleteWorkoutExercises(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<WorkoutExercisesResponse> createWorkoutExercises(@RequestBody WorkoutExercicesRequest workoutExercicesRequest, UriComponentsBuilder uriBuilder) {
        Optional<WorkoutExercisesEntity> workoutExercises = service.createWorkoutExercises(workoutExercicesRequest);
        if(workoutExercises.isPresent()) {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(workoutExercises.get().getExerciceId());
            return ResponseEntity.ok( WorkoutExercicesMapper.toWorkoutExercisesResponse(workoutExercises.get(), exerciseResponse));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutExercisesResponse> updateWorkoutExercises(@PathVariable Long id, @RequestBody WorkoutExercicesRequest workoutExercicesRequest) {
        Optional<WorkoutExercisesEntity> workoutExercises = service.updateWorkoutExercises(id, workoutExercicesRequest);
        if (workoutExercises.isPresent()) {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(workoutExercises.get().getExerciceId());
            return ResponseEntity.ok(WorkoutExercicesMapper.toWorkoutExercisesResponse(workoutExercises.get(), exerciseResponse));
        }
        return ResponseEntity.notFound().build();
    }

}
