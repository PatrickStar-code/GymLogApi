package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.WorkoutSetsMapper;
import com.Gymlog.Controllers.Request.WorkoutSetsRequest;
import com.Gymlog.Controllers.Response.WorkoutSetsResponse;
import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Repository.WorkoutSetsRepository;
import com.Gymlog.Validator.WorkoutSetsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<WorkoutSetsEntity> getAllWorkoutSetsByPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public WorkoutSetsEntity getWorkoutSetsById(Long id) {
        WorkoutSetsEntity  workoutSetsEntity = repository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "WorkoutSets nao encontrado!"));
        return workoutSetsEntity;
    }

    @Transactional
    public void deleteWorkoutSets(Long id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "WorkoutSets nao encontrado!"));

        repository.deleteById(id);
    }



    @Transactional
    public Optional<WorkoutSetsEntity> updateWorkoutSets(WorkoutSetsRequest workoutSetsRequest, Long id) {
        Optional<WorkoutSetsEntity> result = repository.findById(id);
        if(result.isPresent()) {

            WorkoutSetsValidator.validateWorkoutSets(WorkoutSetsMapper.toWorkoutSetsEntity(workoutSetsRequest));
            WorkoutExercisesEntity workoutExercisesEntity = workoutExercisesService.findById(workoutSetsRequest.workoutExercicesId()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "WorkoutExercises nao encontrado!"));

            result.get().setSets(workoutSetsRequest.sets());
            result.get().setReps(workoutSetsRequest.reps());
            result.get().setWeight(workoutSetsRequest.weight());
            result.get().getWorkoutExercises().setId(workoutSetsRequest.workoutExercicesId());
            return Optional.of(repository.save(result.get()));
        }
        return Optional.empty();
    }

    @Transactional
    public WorkoutSetsEntity createWorkoutSets(WorkoutSetsRequest workoutSetsRequest) {
        WorkoutExercisesEntity result = workoutExercisesService.findById(workoutSetsRequest.workoutExercicesId()).orElseThrow(() -> new NotFoundException("NOT_FOUND", "WorkoutExercises nao encontrado!"));

        WorkoutSetsValidator.validateWorkoutSets(WorkoutSetsMapper.toWorkoutSetsEntity(workoutSetsRequest));

            WorkoutSetsEntity entity = WorkoutSetsEntity.builder()
                    .sets(workoutSetsRequest.sets())
                    .reps(workoutSetsRequest.reps())
                    .weight(workoutSetsRequest.weight())
                    .workoutExercises(result)
                    .build();

            return repository.save(entity);
        }
    }

