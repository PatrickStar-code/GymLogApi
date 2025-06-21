package com.Gymlog.Service;

import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Repository.WorkoutSetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutSetsService {

    private final WorkoutSetsRepository repository;
    private final WorkoutExercisesService workoutExercisesService;

    public List<WorkoutSetsEntity> getAllWorkoutSets() {
        return repository.findAll();
    }

    public Optional<WorkoutSetsEntity> getWorkoutSetsById(Long id) {
        Optional<WorkoutSetsEntity> workoutSetsEntity = repository.findById(id);
        if(workoutSetsEntity.isPresent()) return Optional.of(workoutSetsEntity.get());
        return Optional.empty();
    }

    public void deleteWorkoutSets(Long id) {
        repository.deleteById(id);
    }

    public List<WorkoutSetsEntity> findByWorkoutExercicesId(Long id) {
        Optional<WorkoutExercisesEntity> result = workoutExercisesService.findById(id);
        if(result.isPresent()) return repository.findByWorkoutExercisesId(result.get().getId());
        throw new NotFoundException("NOT_FOUND", "WorkoutExercises nao encontrado!");
    }

    public Optional<WorkoutSetsEntity> updateWorkoutSets(WorkoutSetsRequest workoutSetsRequest, Long id) {
        Optional<WorkoutSetsEntity> result = repository.findById(id);
        if(result.isPresent()) {
            result.get().setSets(workoutSetsRequest.sets());
            result.get().setReps(workoutSetsRequest.reps());
            result.get().setWeight(workoutSetsRequest.weight());
            result.get().getWorkoutExercises().setId(workoutSetsRequest.workoutExercicesId());
            return Optional.of(repository.save(result.get()));
        }
        return Optional.empty();
    }

    public Optional<WorkoutSetsEntity> createWorkoutSets(WorkoutSetsRequest workoutSetsRequest) {
        Optional<WorkoutExercisesEntity> result = workoutExercisesService.findById(workoutSetsRequest.workoutExercicesId());
        if(result.isPresent()) {
            WorkoutSetsEntity entity = WorkoutSetsEntity.builder()
                    .sets(workoutSetsRequest.sets())
                    .reps(workoutSetsRequest.reps())
                    .weight(workoutSetsRequest.weight())
                    .workoutExercises(result.get())
                    .build();
            return Optional.of(repository.save(entity));
        }
        return Optional.empty();
    }
}
