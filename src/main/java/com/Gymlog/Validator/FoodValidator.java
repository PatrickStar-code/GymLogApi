package com.Gymlog.Validator;

import com.Gymlog.Entity.FoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public class FoodValidator {
    public static void verifyErrorsFood(FoodEntity foodEntity) {
        if(foodEntity.getFoodName() == "" || foodEntity.getFoodName() == null){
            throw new IllegalArgumentException("Nome da comida nao pode ser vazio ou nulo!");
        }

        if(foodEntity.getGrams() == 0){
            throw new IllegalArgumentException("Gramas nao pode ser zero!");
        }
    }
}
