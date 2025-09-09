package com.Gymlog.Validator;

import com.Gymlog.Entity.MealEntity;
import com.Gymlog.Enums.MealType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class MealsValidator {
    public static void validateMeal(MealEntity mealEntity){

        if(mealEntity.getDateTime() == null){
            throw new IllegalArgumentException("Data e hora da refeição não pode ser nula");
        }

        if(mealEntity.getMealType() == null){
                    throw new IllegalArgumentException("Tipo da refeição não pode ser nulo");
        }

    }
}
