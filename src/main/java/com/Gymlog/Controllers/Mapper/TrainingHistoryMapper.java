package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Controllers.Response.TrainingHistoryResponse;
import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrainingHistoryMapper {

    public static TrainingHistoryResponse toTrainingHistoryResponse(TrainingHistoryEntity trainingHistory) {
        return TrainingHistoryResponse.builder()
                .id(trainingHistory.getId())
                .date(trainingHistory.getOcurrenceDate())
                .user(UserMapper.toUserResponse(trainingHistory.getUserEntity()))
                .workoutPlan(WorkoutPlanMapper.toWorkoutPlanResponse(trainingHistory.getWorkoutPlanEntity()))
                .statusEnum(trainingHistory.getStatus())
                .comment(trainingHistory.getComment())
                .build();
    }

    public static TrainingHistoryEntity toTrainingHistoryEntity(TrainingHistoryRequest trainingHistory, UserEntity userEntity, WorkoutPlanEntity workoutPlanEntity) {
        return TrainingHistoryEntity.builder()
                .ocurrenceDate(trainingHistory.ocurrenceDate())
                .status(trainingHistory.statusEnum())
                .comment(trainingHistory.comment())
                .workoutPlanEntity(workoutPlanEntity)
                .userEntity(userEntity)
                .build();
    }

}
