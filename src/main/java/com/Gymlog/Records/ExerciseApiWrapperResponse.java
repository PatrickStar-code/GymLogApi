package com.Gymlog.Records;

import com.Gymlog.Controllers.Response.ExerciseResponse;

public record ExerciseApiWrapperResponse(
        boolean success,
        ExerciseResponse data
) {

}
