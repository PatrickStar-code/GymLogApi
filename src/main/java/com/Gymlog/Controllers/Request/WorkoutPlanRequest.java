package com.Gymlog.Controllers.Request;

import lombok.Builder;

@Builder
public record WorkoutPlanRequest(
        String name,
        String imageUrl,
        Long userid
){
}
