package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.TrainingHistoryMapper;
import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Repository.TrainingHistoryRepopistory;
import com.Gymlog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  TrainingHistoryService {

    private final TrainingHistoryRepopistory trainingHistoryRepository;
    private final UserRepository userRepository;
    private final WorkoutPlanService workoutPlanService;

    public TrainingHistoryEntity saveTrainingHistory(TrainingHistoryRequest trainingHistory) {

        if(trainingHistory.workoutPlanId() == null) {
            throw new IllegalArgumentException("Workout plan cannot be null");
        }


        if(trainingHistory.userId() == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        var workoutPlan = workoutPlanService.findById(trainingHistory.workoutPlanId()).orElse(null);
        if(workoutPlan == null) {
            throw new IllegalArgumentException("Workout plan not found");
        }

        var user = userRepository.findById(trainingHistory.userId()).orElse(null);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return trainingHistoryRepository.save(TrainingHistoryMapper.toTrainingHistoryEntity(trainingHistory, user, workoutPlan));
    }
}
