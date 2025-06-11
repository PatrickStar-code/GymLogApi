package com.Gymlog.Service;

import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository repository;

    public List<WorkoutPlanEntity> findAll() {
        return repository.findAll();
    }

    public Optional<WorkoutPlanEntity> findById(Long id) {
        Optional<WorkoutPlanEntity> result = repository.findById(id);
        if(result.isPresent()) return result;
        return Optional.empty();
    }

    public Optional<Void> deleteWorkoutPlan(Long id) {
        Optional<WorkoutPlanEntity> result = repository.findById(id);
        if(result.isPresent()) repository.delete(result.get());
        return Optional.empty();
    }
}
