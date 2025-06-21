package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
import com.Gymlog.Entity.WorkoutPlanEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkoutPlanMapper {
    public static WorkoutPlanEntity toWorkoutPlanEntity(WorkoutPlanRequest workoutPlanRequest) {
        return
                WorkoutPlanEntity.builder()
                .name(workoutPlanRequest.name())
                .imageUrl(workoutPlanRequest.imageUrl())
                .build();
    }

    public static WorkoutPlanResponse toWorkoutPlanResponse(WorkoutPlanEntity workoutPlanEntity) {
        return WorkoutPlanResponse.builder()
                .id(workoutPlanEntity.getId())
                .name(workoutPlanEntity.getName())
                .imageUrl(workoutPlanEntity.getImageUrl())
                .userId(workoutPlanEntity.getUser().getUserId())
                .build();
    }
}
