package com.Gymlog.Controllers.Request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TrainingHistoryRequest(
        Long id,
        Long user,
        Long workoutPlan,
        LocalDateTime ocurrenceDate
) {
}
