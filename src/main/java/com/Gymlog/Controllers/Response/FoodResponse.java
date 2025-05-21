package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record FoodResponse(Long id,String foodName, double calories, double proteins, double carbs, double fibers, double fats,double grams) {
}
