package com.Gymlog.Controllers.Request;

public record FoodRequest(Long id,String foodName, double calories, double proteins, double carbs, double fibers, double fats,double grams) {
}
