package com.Gymlog.Controllers.Response;

import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import lombok.Builder;

import java.util.Set;

@Builder
public record WorkoutPlanResponse(Long id,
                                  String name,
                                  String imageUrl,
                                  UserEntity user,
                                  Set<WorkoutExercicesResponse> workoutExercices,
                                  Set<WorkoutSetsEntity> workoutSets) {
}
