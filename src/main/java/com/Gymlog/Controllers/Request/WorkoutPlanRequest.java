package com.Gymlog.Controllers.Request;

import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutExercicesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import lombok.Builder;

import java.util.Set;

@Builder
public record WorkoutPlanRequest(
        String name,
        String imageUrl,
        Long userid
){
}
