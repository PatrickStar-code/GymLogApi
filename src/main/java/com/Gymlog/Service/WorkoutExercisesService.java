package com.Gymlog.Service;

import com.Gymlog.Entity.WorkoutExercicesEntity;
import com.Gymlog.Repository.WorkoutExercicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutExercisesService {

    private final WorkoutExercicesRepository repository;

    public List<WorkoutExercicesEntity> getWorkoutExercises() {
        return repository.findAll();
    }
}
