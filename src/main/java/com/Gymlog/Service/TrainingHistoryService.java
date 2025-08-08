package com.Gymlog.Service;

import com.Gymlog.Repository.TrainingHistoryRepopistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  TrainingHistoryService {

    private final TrainingHistoryRepopistory trainingHistoryRepository;

}
