package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record WorkoutSetsResponse(
        Long id,
        int sets,
        int reps,
        double weight,
        Long workoutExercicesId
) {
}
