package com.Gymlog.Controllers.Request;

import lombok.Builder;

@Builder
public record WorkoutSetsRequest(
        int reps,
        int sets,
        double weight,
        Long workoutExercicesId
) {
}
