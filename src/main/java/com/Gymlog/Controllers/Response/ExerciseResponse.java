package com.Gymlog.Controllers.Response;

import lombok.Data;

import java.util.List;

public record ExerciseResponse(
        String exerciseId,
        String name,
        String gifUrl,
        List<String> instructions,
        List<String> targetMuscles,
        List<String> bodyParts,
        List<String> equipments,
        List<String> secondaryMuscles
) {
}
