package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Controllers.Response.TrainingHistoryResponse;
import com.Gymlog.Entity.TrainingHistoryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrainingHistoryMapper {

    public static TrainingHistoryResponse toTrainingHistoryResponse(TrainingHistoryEntity trainingHistory) {
        return TrainingHistoryResponse.builder()
                .id(trainingHistory.getId())
                .date(trainingHistory.getOcurrenceDate())
                .user(UserMapper.toUserResponse(trainingHistory.getUserEntity()))
                .workoutPlan(WorkoutPlanMapper.toWorkoutPlanResponse(trainingHistory.getWorkoutPlanEntity()))
                .build();
    }

    public static TrainingHistoryEntity toTrainingHistoryEntity(TrainingHistoryRequest trainingHistory) {
        return TrainingHistoryEntity.builder()
                .ocurrenceDate(trainingHistory.ocurrenceDate())
                .build();
    }

}
