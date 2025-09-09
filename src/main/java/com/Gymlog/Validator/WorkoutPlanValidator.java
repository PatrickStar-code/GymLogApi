package com.Gymlog.Validator;

import com.Gymlog.Entity.WorkoutPlanEntity;

public class WorkoutPlanValidator {
    public static void validateWorkoutPlan(WorkoutPlanEntity workoutPlanEntity){
        if(workoutPlanEntity.getName() == "" || workoutPlanEntity.getName() == null){
            throw new RuntimeException("Name is required");
        }
    }
}
