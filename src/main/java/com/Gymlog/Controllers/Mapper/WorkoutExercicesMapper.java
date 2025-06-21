package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.WorkoutExercicesRequest;
import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.ExerciseResponse;
import com.Gymlog.Controllers.Response.WorkoutExercisesResponse;
import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkoutExercicesMapper {
    public  static WorkoutExercisesResponse toWorkoutExercisesResponse(WorkoutExercisesEntity workoutExercisesEntity, ExerciseResponse exerciseResponse){
        return WorkoutExercisesResponse.builder()
                .id(workoutExercisesEntity.getId())
                .workoutPlanId(workoutExercisesEntity.getWorkoutPlan().getId())
                .exerciseResponse(exerciseResponse)
                .build();
    }

    public static WorkoutExercisesEntity toWorkoutExercisesEntity(WorkoutExercicesRequest workoutExercicesRequest, WorkoutPlanEntity workoutPlanEntity)
        {
            return WorkoutExercisesEntity.builder()
                    .exerciceId(workoutExercicesRequest.exerciceId())
                    .workoutPlan(workoutPlanEntity)
                    .build();
        }
    }

