package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.WorkoutExercicesResponse;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutExercicesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class WorkoutPlanMapper {
    public static WorkoutPlanEntity toWorkoutPlanEntity(WorkoutPlanRequest workoutPlanRequest, UserEntity userEntity) {
        return
                WorkoutPlanEntity.builder()
                .name(workoutPlanRequest.name())
                .imageUrl(workoutPlanRequest.imageUrl())
                .user(userEntity)
                .workoutExercices((Set<WorkoutExercicesEntity>) workoutPlanRequest.workoutExercices())
                .workoutSets((Set<WorkoutSetsEntity>) workoutPlanRequest.workoutSets())
                .build();
    }

    public static WorkoutPlanResponse toWorkoutPlanResponse(WorkoutPlanEntity workoutPlanEntity) {

        WorkoutExercicesResponse workoutExercicesResponse = WorkoutExercicesMapper.toWorkoutExercicesResponse((WorkoutExercicesEntity) workoutPlanEntity.getWorkoutExercices());

        return WorkoutPlanResponse.builder()
                .id(workoutPlanEntity.getId())
                .name(workoutPlanEntity.getName())
                .imageUrl(workoutPlanEntity.getImageUrl())
                .user(workoutPlanEntity.getUser())
                .workoutExercices(workoutExercicesResponse)
                //.workoutSets(workoutPlanEntity.getWorkoutSets())
                .build();
    }
}
