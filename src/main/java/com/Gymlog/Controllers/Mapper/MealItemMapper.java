package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.MealItemRequest;
import com.Gymlog.Controllers.Response.MealItemResponse;
import com.Gymlog.Entity.MealItemEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MealItemMapper {

    public static MealItemResponse toResponse(MealItemEntity mealItemEntity){
        return MealItemResponse.builder()
                .id(mealItemEntity.getId())
                .quantity(mealItemEntity.getQuantity())
                .food(FoodMapper.toFoodResponse(mealItemEntity.getFood()))
                .meals(MealsMapper.toMealsResponse(mealItemEntity.getMeal()))
                .build();
    }

    public  static  MealItemEntity toEntity(MealItemRequest mealItemRequest){
        return MealItemEntity.builder()
                .quantity(mealItemRequest.quantity())
                .build();
    }

}
