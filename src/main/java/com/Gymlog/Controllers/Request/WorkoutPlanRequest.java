package com.Gymlog.Controllers.Request;

import com.Gymlog.Controllers.Response.WorkoutExercicesResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import lombok.Builder;

import java.util.Set;

@Builder
public record WorkoutPlanRequest(
        String name,
        String imageUrl,
        UserEntity user,
        Set<WorkoutExercicesResponse> workoutExercices,
        Set<WorkoutSetsEntity> workoutSets
){
}
