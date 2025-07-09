package com.Gymlog.Controllers.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WorkoutExercisesResponse(
        @Schema(description = "Workout exercise id", example = "1")
    Long id,

    @Schema(description = "Workout exercise exercise", example = "Workout exercise exercise")
    ExerciseResponse exerciseResponse,

    @Schema(description = "Workout exercise workout plan id", example = "1")
    Long workoutPlanId
) {
}
