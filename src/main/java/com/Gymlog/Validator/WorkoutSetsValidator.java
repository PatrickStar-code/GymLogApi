package com.Gymlog.Validator;

import com.Gymlog.Entity.WorkoutSetsEntity;

public class WorkoutSetsValidator {
    public static void validateWorkoutSets(WorkoutSetsEntity workoutSetsEntity){
        if(workoutSetsEntity.getSets() == 0){
            throw new IllegalArgumentException("Sets nao pode ser zero!");
        }

        if(workoutSetsEntity.getReps() == 0){
            throw new IllegalArgumentException("Reps nao pode ser zero!");
        }

    }
}
