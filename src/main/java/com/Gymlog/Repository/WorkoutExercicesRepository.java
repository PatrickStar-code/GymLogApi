package com.Gymlog.Repository;

import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExercicesRepository extends JpaRepository<WorkoutExercisesEntity,Long> {
    List<WorkoutExercisesEntity> findByWorkoutPlan(WorkoutPlanEntity workoutPlanEntity);
}
