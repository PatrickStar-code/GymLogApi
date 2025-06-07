package com.Gymlog.Controllers.Request;

import lombok.Builder;

@Builder
public record WorkoutExercicesRequest (
        String exerciceId,
        double weight,
        Long workoutPlanId
){
}
