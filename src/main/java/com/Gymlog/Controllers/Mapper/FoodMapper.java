package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.FoodRequest;
import com.Gymlog.Controllers.Response.FoodResponse;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodMapper {

    public static FoodEntity toFoodEntity(FoodRequest foodResponse) {
        return FoodEntity.builder()
                .id(foodResponse.id())
                .foodName(foodResponse.foodName())
                .calories(foodResponse.calories())
                .proteins(foodResponse.proteins())
                .carbs(foodResponse.carbs())
                .fibers(foodResponse.fibers())
                .fats(foodResponse.fats())
                .grams(foodResponse.grams())
                .build();
    }

    public static FoodResponse toFoodResponse(FoodEntity foodEntity) {
        return FoodResponse.builder()
                .id(foodEntity.getId())
                .foodName(foodEntity.getFoodName())
                .calories(foodEntity.getCalories())
                .proteins(foodEntity.getProteins())
                .carbs(foodEntity.getCarbs())
                .fibers(foodEntity.getFibers())
                .fats(foodEntity.getFats())
                .grams(foodEntity.getGrams())
                .build();
    }
}
