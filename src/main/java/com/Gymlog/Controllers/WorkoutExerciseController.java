package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutExercicesMapper;
import com.Gymlog.Controllers.Request.WorkoutExercicesRequest;
import com.Gymlog.Controllers.Response.ExerciseResponse;
import com.Gymlog.Controllers.Response.WorkoutExercisesResponse;
import com.Gymlog.Controllers.SwaggerInterface.WorkoutExercisesControllerInterface;
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
public class WorkoutExerciseController implements WorkoutExercisesControllerInterface {

    private final WorkoutExercisesService service;
    private final ExerciseApiService exerciseApiService;



    @Override
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

    @Override
    public ResponseEntity<WorkoutExercisesResponse> getWorkoutExercisesById(Long id) {
        Optional<WorkoutExercisesEntity> workoutExercises = service.findById(id);
        if(workoutExercises.isPresent()) {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(workoutExercises.get().getExerciceId());
            WorkoutExercisesResponse workoutExercisesResponse = WorkoutExercicesMapper.toWorkoutExercisesResponse(workoutExercises.get(), exerciseResponse);
            return ResponseEntity.ok(workoutExercisesResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<WorkoutExercisesResponse>> getWorkoutExercisesByWorkoutPlanId(Long id) {
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

    @Override
    public ResponseEntity<Void> deleteWorkoutExercises(Long id) {
        Optional<WorkoutExercisesEntity> result = service.findById(id);
        if(result.isPresent()) {
            service.deleteWorkoutExercises(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WorkoutExercisesResponse> createWorkoutExercises(WorkoutExercicesRequest workoutExercicesRequest, UriComponentsBuilder uriBuilder) {
        Optional<WorkoutExercisesEntity> workoutExercises = service.createWorkoutExercises(workoutExercicesRequest);
        if(workoutExercises.isPresent()) {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(workoutExercises.get().getExerciceId());
            var uri = uriBuilder.path("/GymLog/workoutExercise/{id}").buildAndExpand(workoutExercises.get().getId()).toUri();
            return ResponseEntity.created(uri).body(WorkoutExercicesMapper.toWorkoutExercisesResponse(workoutExercises.get(), exerciseResponse));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WorkoutExercisesResponse> updateWorkoutExercises(Long id, WorkoutExercicesRequest workoutExercicesRequest) {
        Optional<WorkoutExercisesEntity> workoutExercises = service.updateWorkoutExercises(id, workoutExercicesRequest);
        if (workoutExercises.isPresent()) {
            ExerciseResponse exerciseResponse = exerciseApiService.getExerciseById(workoutExercises.get().getExerciceId());
            return ResponseEntity.ok(WorkoutExercicesMapper.toWorkoutExercisesResponse(workoutExercises.get(), exerciseResponse));
        }
        return ResponseEntity.notFound().build();

    }
}
