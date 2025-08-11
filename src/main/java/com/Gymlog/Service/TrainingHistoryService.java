package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.TrainingHistoryMapper;
import com.Gymlog.Controllers.Request.TrainingHistoryRequest;
import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Repository.TrainingHistoryRepopistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  TrainingHistoryService {

    private final TrainingHistoryRepopistory trainingHistoryRepository;


    public TrainingHistoryEntity saveTrainingHistory(TrainingHistoryRequest trainingHistory) {
        return trainingHistoryRepository.save(TrainingHistoryMapper.toTrainingHistoryEntity(trainingHistory));
    }
}
