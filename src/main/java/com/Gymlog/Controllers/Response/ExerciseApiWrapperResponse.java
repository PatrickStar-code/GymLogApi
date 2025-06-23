package com.Gymlog.Controllers.Response;

public record ExerciseApiWrapperResponse(
        boolean success,
        ExerciseResponse data
) {

}
