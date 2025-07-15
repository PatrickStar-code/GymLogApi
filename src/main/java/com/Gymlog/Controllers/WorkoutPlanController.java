package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.WorkoutPlanMapper;
import com.Gymlog.Controllers.Request.WorkoutPlanRequest;
import com.Gymlog.Controllers.Response.WorkoutPlanResponse;
import com.Gymlog.Controllers.SwaggerInterface.WorkoutPlanControllerInterface;
import com.Gymlog.Entity.WorkoutPlanEntity;
import com.Gymlog.Repository.WorkoutPlanRepository;
import com.Gymlog.Service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/GymLog/workoutPlan")
@RequiredArgsConstructor
public class WorkoutPlanController implements WorkoutPlanControllerInterface {

    private final WorkoutPlanService service;


    @Override
    public ResponseEntity<Page<WorkoutPlanResponse>> getWorkoutPlan(int page, int size) {
        if(page >= 0 && size > 0) {
            Page<WorkoutPlanEntity> workoutPlans = service.getAllWorkoutPlanByPage(page, size);
            Page<WorkoutPlanResponse> workoutPlanResponseStream =  workoutPlans.map(WorkoutPlanMapper::toWorkoutPlanResponse);
            return ResponseEntity.ok().body(new PageImpl<>(workoutPlanResponseStream.toList(), PageRequest.of(page, size),workoutPlans.getTotalElements()));
        }
        List<WorkoutPlanEntity> workoutPlans = service.findAll();
        List<WorkoutPlanResponse> workoutPlanResponseStream = workoutPlans.stream().map(WorkoutPlanMapper::toWorkoutPlanResponse).toList();
        PageImpl<WorkoutPlanResponse> workoutPlanResponsePage = new PageImpl<>(workoutPlanResponseStream, PageRequest.of(0,workoutPlans.size() > 0 ? workoutPlans.size() : 1),workoutPlans.size() > 0 ? workoutPlans.size() : 1);
        return ResponseEntity.ok(workoutPlanResponsePage);
    }

    @Override
    public ResponseEntity<WorkoutPlanResponse> getWorkoutPlanById(Long id) {
        Optional<WorkoutPlanEntity> workoutPlan = service.findById(id);
        if(workoutPlan.isPresent()){
            return ResponseEntity.ok(WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlan.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WorkoutPlanResponse> updateWorkoutPlan(Long id, WorkoutPlanRequest workoutPlanRequest) {
        Optional<WorkoutPlanEntity> workoutPlan = service.updateWorkoutPlan(id, workoutPlanRequest);
        if(workoutPlan.isPresent()){
            return ResponseEntity.ok(WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlan.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Page<WorkoutPlanResponse>> getWorkoutPlanByUser(Long id, int page, int size) {
        if(page >= 0 && size > 0) {
            Page<WorkoutPlanEntity> workoutPlans = service.getAllWorkoutPlanUserByPage(page, size,id);
            Page<WorkoutPlanResponse> workoutPlanResponseStream =  workoutPlans.map(WorkoutPlanMapper::toWorkoutPlanResponse);
            return ResponseEntity.ok().body(new PageImpl<>(workoutPlanResponseStream.toList(), PageRequest.of(page, size),workoutPlans.getTotalElements()));
        }
        List<WorkoutPlanEntity> workoutPlans = service.findByUser(id);
        List<WorkoutPlanResponse> workoutPlanResponseStream = workoutPlans.stream().map(WorkoutPlanMapper::toWorkoutPlanResponse).toList();
        PageImpl<WorkoutPlanResponse> workoutPlanResponsePage = new PageImpl<>(workoutPlanResponseStream, PageRequest.of(0,workoutPlans.size() > 0 ? workoutPlans.size() : 1),workoutPlans.size() > 0 ? workoutPlans.size() : 1);
        return ResponseEntity.ok(workoutPlanResponsePage);
    }


    @Override
    public ResponseEntity<WorkoutPlanResponse> createWorkoutPlan(WorkoutPlanRequest workoutPlanRequest, UriComponentsBuilder uriBuilder) {
        WorkoutPlanEntity workoutPlanEntity = service.createWorkoutPlan(workoutPlanRequest);
        WorkoutPlanResponse workoutPlanResponse = WorkoutPlanMapper.toWorkoutPlanResponse(workoutPlanEntity);
        var uri = uriBuilder.path("/GymLog/workoutPlan/{id}").buildAndExpand(workoutPlanResponse.id()).toUri();
        return ResponseEntity.created(uri).body(workoutPlanResponse);
    }

    @Override
    public ResponseEntity<Void> deleteWorkoutPlan(Long id) {
        service.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }
}
