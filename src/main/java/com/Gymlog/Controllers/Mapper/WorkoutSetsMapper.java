package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.WorkoutSetsEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkoutSetsMapper {

    public static WorkoutSetsEntity toWorkoutSetsEntity(WorkoutSetsRequest workoutSetsRequest) {
        return WorkoutSetsEntity.builder()
                .reps(workoutSetsRequest.reps())
                .sets(workoutSetsRequest.sets())
                .weight(workoutSetsRequest.weight())
                .build();
    }

    public static WorkoutSetsResponse toWorkoutSetsResponse(WorkoutSetsEntity workoutSetsEntity) {
        return WorkoutSetsResponse.builder()
                .id(workoutSetsEntity.getId())
                .sets(workoutSetsEntity.getSets())
                .reps(workoutSetsEntity.getReps())
                .weight(workoutSetsEntity.getWeight())
                .workoutPlanId(workoutSetsEntity.getWorkoutExercices().getWorkoutPlan().getId())
                .build();
    }

}
