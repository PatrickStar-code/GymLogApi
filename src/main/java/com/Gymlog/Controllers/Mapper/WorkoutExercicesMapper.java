package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.WorkoutExercicesRequest;
import com.Gymlog.Controllers.Response.WorkoutExercicesResponse;
import com.Gymlog.Entity.WorkoutExercicesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkoutExercicesMapper {

    public static WorkoutExercicesEntity toWorkoutExercicesEntity(WorkoutExercicesRequest workoutExercicesRequest, WorkoutPlanEntity workoutPlanEntity) {
        return WorkoutExercicesEntity.builder()
                .exerciceId(workoutExercicesRequest.exerciceId())
                .weight(workoutExercicesRequest.weight())
                .workoutPlan(workoutPlanEntity)
                .build();
    }


    public static WorkoutExercicesResponse toWorkoutExercicesResponse(WorkoutExercicesEntity workoutExercicesEntity) {
        return WorkoutExercicesResponse.builder()
                .id(workoutExercicesEntity.getId())
                .exerciceId(workoutExercicesEntity.getExerciceId())
                .weight(workoutExercicesEntity.getWeight())
                .workoutPlanId(workoutExercicesEntity.getWorkoutPlan().getId())
                .build();
    }
}
