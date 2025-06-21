package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.WorkoutExercicesMapper;
import com.Gymlog.Controllers.Request.WorkoutExercicesRequest;
import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Repository.WorkoutExercicesRepository;
import com.Gymlog.Repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutExercisesService {

    private final WorkoutExercicesRepository repository;
    private final WorkoutPlanRepository workoutPlanRepository;
    private final ExerciseApiService exerciseApiService;

    public List<WorkoutExercisesEntity> getWorkoutExercises() {
        return repository.findAll();
    }

    public Optional<WorkoutExercisesEntity> findById(Long id) {
        Optional<WorkoutExercisesEntity> result = repository.findById(id);
        if(result.isPresent()) return result;
        return Optional.empty();
    }

    public List<WorkoutExercisesEntity> findByWorkoutPlanId(Long id) {
        Optional<WorkoutPlanEntity> result = workoutPlanRepository.findById(id);
        if(result.isPresent()) return repository.findByWorkoutPlan(result.get());
        return null;

    }

    public void deleteWorkoutExercises(Long id) {
        repository.deleteById(id);
    }

    public Optional<WorkoutExercisesEntity> createWorkoutExercises(WorkoutExercicesRequest workoutExercicesRequest) {
        Optional<WorkoutPlanEntity> workoutPlanEntity = workoutPlanRepository.findById(workoutExercicesRequest.workoutPlanId());
        if(workoutPlanEntity.isPresent()) {
            return Optional.of(repository.save(WorkoutExercicesMapper.toWorkoutExercisesEntity(workoutExercicesRequest, workoutPlanEntity.get())));
        }
        return Optional.empty();
    }


    public Optional<WorkoutExercisesEntity> updateWorkoutExercises(Long id, WorkoutExercicesRequest workoutExercicesRequest) {
        Optional<WorkoutPlanEntity> workoutPlanEntity = workoutPlanRepository.findById(workoutExercicesRequest.workoutPlanId());
        if(workoutPlanEntity.isPresent()) {
            Optional<WorkoutExercisesEntity> result = repository.findById(id);
            if(result.isPresent()) {
                System.out.println(workoutExercicesRequest.exerciceId());
                result.get().setExerciceId(workoutExercicesRequest.exerciceId());
                result.get().setWorkoutPlan(workoutPlanEntity.get());
                return Optional.of(repository.save(result.get()));
            }
        }
        return Optional.empty();
    }
}
