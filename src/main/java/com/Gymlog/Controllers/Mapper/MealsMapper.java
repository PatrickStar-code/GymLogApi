package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.MealsRequest;
import com.Gymlog.Controllers.Response.MealsResponse;
import com.Gymlog.Entity.MealEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MealsMapper {

    public static MealEntity toMealEntity(MealsRequest mealRequest) {
        return MealEntity.builder()
                .dateTime(mealRequest.dateTime())
                .mealType(mealRequest.mealType())
                .proteins(mealRequest.proteins())
                .carbs(mealRequest.carbs())
                .fats(mealRequest.fats())
                .fats(mealRequest.fats())
                .calories(mealRequest.calories())
                .build();
    }

    public static MealsResponse toMealsResponse(MealEntity mealEntity) {


        return MealsResponse.builder()
                .id(mealEntity.getId())
                .dateTime(mealEntity.getDateTime())
                .mealType(mealEntity.getMealType())
                .calories(mealEntity.getCalories())
                .proteins(mealEntity.getProteins())
                 .carbs(mealEntity.getCarbs())
                .fats(mealEntity.getFats())
                .user(UserMapper.toUserResponse(mealEntity.getUser()))
                .build();
    }

}
