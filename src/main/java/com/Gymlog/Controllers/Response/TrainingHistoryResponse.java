package com.Gymlog.Controllers.Response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TrainingHistoryResponse(
        Long id,
        LocalDateTime date,
        UserResponse user ,
        WorkoutPlanResponse workoutPlan
) {
}
