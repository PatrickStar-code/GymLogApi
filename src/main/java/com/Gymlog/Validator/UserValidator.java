package com.Gymlog.Validator;

import com.Gymlog.Entity.UserEntity;

import java.time.LocalDateTime;

public class UserValidator {

    public static void validateUser(UserEntity userEntity) {

        if(userEntity.getUsername() == "" || userEntity.getUsername() == null){
            throw new RuntimeException("Username is required");
        }

        if(userEntity.getHeight() == 0){
            throw new RuntimeException("Height is required");
        }

        if(userEntity.getWeight() == 0){
            throw new RuntimeException("Weight is required");
        }

        if(userEntity.getGoal() == null){
            throw new RuntimeException("Goal is required");
        }

        if(userEntity.getGoalWeight() == 0){
            throw new RuntimeException("Goal Weight is required");
        }

        if(userEntity.getActivyLevel() == null){
            throw new RuntimeException("Activity Level is required");
        }



    }
}
