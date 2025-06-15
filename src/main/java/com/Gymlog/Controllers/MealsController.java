package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.MealsMapper;
import com.Gymlog.Controllers.Request.MealsRequest;
import com.Gymlog.Controllers.Response.MealsResponse;
import com.Gymlog.Entity.MealEntity;
import com.Gymlog.Service.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/meals")
@RequiredArgsConstructor
public class MealsController {

    private final MealsService mealsService;

    @GetMapping("/")
    public ResponseEntity<List<MealsResponse>> getMeals() {
        List<MealEntity> meals = mealsService.getMeals();
        return ResponseEntity.ok(meals.stream().map(MealsMapper::toMealsResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealsResponse> getMealsById(@PathVariable Long id) {
        Optional<MealEntity> meal = mealsService.getMealsById(id);
        return meal.map(MealsMapper::toMealsResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        Optional<Void> result = mealsService.deleteMeals(id);
        if(result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealsResponse> updateMeals(@RequestBody MealsRequest mealsRequest , @PathVariable Long id) {
        Optional<MealEntity> meal = mealsService.updateMeals(id,mealsRequest);
        return meal.map(MealsMapper::toMealsResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<MealsResponse> createMeals(@RequestBody MealsRequest mealsRequest) {
        Optional<MealEntity> meal = mealsService.createMeals(mealsRequest);
        return meal.map(MealsMapper::toMealsResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
