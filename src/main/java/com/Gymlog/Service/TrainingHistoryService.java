package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.TrainingHistoryMapper;
import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Enums.StatusEnum;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Repository.TrainingHistoryRepopistory;
import com.Gymlog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        if(trainingHistory.ocurrenceDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        if(trainingHistory.statusEnum() == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }



        return trainingHistoryRepository.save(TrainingHistoryMapper.toTrainingHistoryEntity(trainingHistory, user, workoutPlan));
    }

    public Page<TrainingHistoryEntity> getAllTrainingHistoryByPage(int page, int size) {
        return trainingHistoryRepository.findAll(PageRequest.of(page, size));
    }

    public List<TrainingHistoryEntity> getAllTrainingHistory() {
        return trainingHistoryRepository.findAll();
    }


    public void deleteTrainingHistory(Long id) {
        TrainingHistoryEntity trainingHistoryEntity = trainingHistoryRepository.findById(id).orElse(null);
        if(trainingHistoryEntity == null) {
            throw new IllegalArgumentException("Training history not found");
        }
        trainingHistoryRepository.delete(trainingHistoryEntity);
    }

    public Optional<TrainingHistoryEntity>  updateTrainingHistory(Long id, TrainingHistoryRequest trainingHistoryRequest) {
        Optional<TrainingHistoryEntity> trainingHistoryEntity = trainingHistoryRepository.findById(id);


        if(!trainingHistoryEntity.isEmpty()) {
            TrainingHistoryEntity trainingHistoryEntity1 = trainingHistoryEntity.get();
            trainingHistoryEntity1.setWorkoutPlanEntity(workoutPlanService.findById(trainingHistoryRequest.workoutPlanId()).orElseThrow(()-> new NotFoundException("NOT_FOUND", "Workout plan not found")));
            trainingHistoryEntity1.setUserEntity(userRepository.findById(trainingHistoryRequest.userId()).orElseThrow(()-> new NotFoundException("NOT_FOUND", "User not found")));
            trainingHistoryEntity1.setComment(trainingHistoryRequest.comment());
            trainingHistoryEntity1.setOcurrenceDate(trainingHistoryRequest.ocurrenceDate());

            if(StatusEnum.valueOf( trainingHistoryRequest.statusEnum().name()) == null) {
                throw new IllegalArgumentException("Status not exists is not valid");
            }
            trainingHistoryEntity1.setStatus(trainingHistoryRequest.statusEnum());
            return Optional.of(trainingHistoryRepository.save(trainingHistoryEntity1));
        }
        else {
           return Optional.empty();
        }
    }

    public TrainingHistoryEntity getTrainingHistoryById(Long id) {
        TrainingHistoryEntity trainingHistoryEntity = trainingHistoryRepository.findById(id).orElseThrow( () -> new NotFoundException("NOT_FOUND","Training history not found"));
        return trainingHistoryEntity;
    }

    public Page<TrainingHistoryEntity> getAllTrainingHistoryByUserPage(int page, int size, Long id) {
        userRepository.findById(id).orElseThrow(()-> new NotFoundException("NOT_FOUND", "User not found"));

        Page<TrainingHistoryEntity> trainingHistoryEntities = trainingHistoryRepository.findAllByUserEntity_UserId(PageRequest.of(page, size), id);
        return trainingHistoryEntities;
    }



    public List<TrainingHistoryEntity> getAllTrainingHistoryByUser(Long id) {
        List<TrainingHistoryEntity> trainingHistoryEntities = trainingHistoryRepository.findAllByUserEntity_UserId(id);
        return trainingHistoryEntities;
    }


    public Page<TrainingHistoryEntity> getAllTrainingHistoryByMonthAndYearPage(int page, int size, int month, int year, Long userId) {
        if(userId == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        if(year < 1) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }

        userRepository.findById(userId).orElseThrow(()-> new NotFoundException("NOT_FOUND", "User not found"));

        Page<TrainingHistoryEntity> trainingHistoryEntities = trainingHistoryRepository.findAllByUserEntity_UserIdAndByMonthAndByYear(PageRequest.of(page, size), userId, month, year);
        return trainingHistoryEntities;
    }

    public List<TrainingHistoryEntity> getAllTrainingHistoryByMonthAndYear(int month, int year) {
        List<TrainingHistoryEntity> trainingHistoryEntities = trainingHistoryRepository.findAllByByMonthAndByYear(month, year);
        return trainingHistoryEntities;
    }
}
