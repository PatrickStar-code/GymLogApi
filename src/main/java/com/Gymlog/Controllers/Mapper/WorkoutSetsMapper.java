package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.WorkoutSetsEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkoutSetsMapper {

    public static WorkoutSetsResponse toWorkoutSetsResponse(WorkoutSetsEntity entity) {
        return WorkoutSetsResponse.builder()
                .id(entity.getId())
                .sets(entity.getSets())
                .reps(entity.getReps())
                .weight(entity.getWeight())
                .workoutExercicesId(entity.getWorkoutExercises().getId())
                .build();
    }

    public static  WorkoutSetsEntity toWorkoutSetsEntity(WorkoutSetsRequest request) {
        return WorkoutSetsEntity.builder()
                .sets(request.sets())
                .reps(request.reps())
                .weight(request.weight())
                .build();
    }
}
