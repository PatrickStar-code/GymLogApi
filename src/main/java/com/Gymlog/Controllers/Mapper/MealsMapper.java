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
                .build();
    }

    public static MealsResponse toMealsResponse(MealEntity mealEntity) {


        return MealsResponse.builder()
                .id(mealEntity.getId())
                .dateTime(mealEntity.getDateTime())
                .mealType(mealEntity.getMealType())
                .user(UserMapper.toUserResponse(mealEntity.getUser()))
                .build();
    }

}
