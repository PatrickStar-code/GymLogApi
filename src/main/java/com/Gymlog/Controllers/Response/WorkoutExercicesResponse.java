package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record WorkoutExercicesResponse(
        Long id,
        String exerciceId,
        Long workoutPlanId
) {
}
