package com.Gymlog.Controllers.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WorkoutPlanResponse(
        @Schema(description = "Workout plan id", example = "1")
        Long id,

        @Schema(description = "Workout plan name", example = "Workout plan name")
        String name,

        @Schema(description = "Workout plan image url", example = "Workout plan image url")
        String imageUrl,

        @Schema(description = "Workout plan user id", example = "Workout plan user id")
        Long userId
                            ) {
}
