package com.Gymlog.Controllers.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WorkoutExercicesRequest
        (
                @Schema(example = "BReCuOn",description = "Exercice id")
                String exerciceId,

                @Schema(example = "1",description = "Workout plan id")
                Long workoutPlanId
        ){
}
