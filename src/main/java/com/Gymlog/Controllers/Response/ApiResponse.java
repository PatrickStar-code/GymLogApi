package com.Gymlog.Controllers.Response;

public record ApiResponse(
        boolean success,
        ExerciseResponse data
) {
}
