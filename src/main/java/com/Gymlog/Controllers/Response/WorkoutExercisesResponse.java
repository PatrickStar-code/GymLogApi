package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record WorkoutExercisesResponse(
    Long id,
    ExerciseResponse exerciseResponse,
    Long workoutPlanId
) {
}
