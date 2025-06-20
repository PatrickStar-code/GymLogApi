package com.Gymlog.Controllers.Request;

import lombok.Builder;

@Builder
public record WorkoutExercicesRequest
        (
                Long id,
                String exerciceId,
                Long workoutPlanId
        ){
}
