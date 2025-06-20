package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Response.ExerciseResponse;
import com.Gymlog.Controllers.Response.WorkoutExercisesResponse;
import com.Gymlog.Entity.WorkoutExercicesEntity;
import com.Gymlog.Service.ExerciseApiService;
import com.Gymlog.Service.WorkoutExercisesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/GymLog/workoutExercise")
@RequiredArgsConstructor
public class WorkoutExerciseController {

    private final WorkoutExercisesService service;
    private final ExerciseApiService exerciseApiService;

    @GetMapping("/")
    public ResponseEntity<List<WorkoutExercisesResponse>> getWorkoutExercises() {
        List<WorkoutExercicesEntity> workoutExercicesEntity = service.getWorkoutExercises();

        List<WorkoutExercisesResponse> response = workoutExercicesEntity.stream().map(entity -> {
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


}
