package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Response.ExerciseResponse;
import com.Gymlog.Controllers.Response.WorkoutExercisesResponse;
import com.Gymlog.Entity.WorkoutExercicesEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkoutExercicesMapper {
    public  static WorkoutExercisesResponse toWorkoutExercisesResponse(WorkoutExercicesEntity workoutExercicesEntity, ExerciseResponse exerciseResponse){
        return WorkoutExercisesResponse.builder()
                .id(workoutExercicesEntity.getId())
                .workoutPlanId(workoutExercicesEntity.getWorkoutPlan().getId())
                .exerciseResponse(exerciseResponse)
                .build();
    }
}

