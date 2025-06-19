package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.WorkoutPlanMapper;
import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Repository.UserRepository;
import com.Gymlog.Repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository repository;
    private final UserRepository userRepository;


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


    public WorkoutPlanEntity createWorkoutPlan(WorkoutPlanRequest workoutPlanRequest) {
        System.out.println(workoutPlanRequest);
        UserEntity user = userRepository.findById(workoutPlanRequest.userid()).orElseThrow( () -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));

        WorkoutPlanEntity workoutPlanEntity = WorkoutPlanMapper.toWorkoutPlanEntity(workoutPlanRequest);
        workoutPlanEntity.setUser(user);
        return repository.save(workoutPlanEntity);
    }

    public Optional<WorkoutPlanEntity> updateWorkoutPlan(Long id, WorkoutPlanRequest workoutPlanRequest) {
        Optional<WorkoutPlanEntity> result = repository.findById(id);
        if(result.isPresent()) {
            WorkoutPlanEntity workoutPlanEntity = result.get();
            workoutPlanEntity.setName(workoutPlanRequest.name());
            workoutPlanEntity.setImageUrl(workoutPlanRequest.imageUrl());
            return Optional.of(repository.save(workoutPlanEntity));
        }
        return Optional.empty();
    }

    public List<WorkoutPlanEntity> findByUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));
        return repository.findByUser(user);
    }
}
