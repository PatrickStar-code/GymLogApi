package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WorkoutSetsRequest(
        @Schema(example = "3",description = "Workout set reps")
        int reps,

        @Schema(example = "3",description = "Workout set sets")
        int sets,

        @Schema(example = "80.0",description = "Workout set weight")
        double weight,

        @Schema(example = "1",description = "Workout set exercise id")
        Long workoutExercicesId
) {
}
