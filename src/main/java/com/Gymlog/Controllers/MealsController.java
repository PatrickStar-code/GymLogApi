package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.MealsMapper;
import com.Gymlog.Controllers.Request.MealsRequest;
import com.Gymlog.Controllers.Response.MealsResponse;
import com.Gymlog.Controllers.SwaggerInterface.MealControllerInterface;
import com.Gymlog.Entity.MealEntity;
import com.Gymlog.Service.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/GymLog/meals")
@RequiredArgsConstructor
public class MealsController implements MealControllerInterface {

    private final MealsService mealsService;


    @Override
    public ResponseEntity<Page<MealsResponse>> getMeals(int page, int size) {
        if(page >= 0 && size > 0) {
            Page<MealEntity> meals = mealsService.getAllMealsByPage(page, size);
            Page<MealsResponse> mealsResponseStream =  meals.map(MealsMapper::toMealsResponse);
            return ResponseEntity.ok().body(mealsResponseStream);
        }
        List<MealEntity> meals = mealsService.getMeals();
        Stream<MealsResponse> mealsResponseStream = meals.stream().map(MealsMapper::toMealsResponse);
        PageImpl<MealsResponse> mealsResponsePage = new PageImpl<>(mealsResponseStream.toList(), PageRequest.of(0,meals.size() > 0 ? meals.size() : 1),  meals.size() > 0 ? meals.size() : 1);
        return ResponseEntity.ok(mealsResponsePage);
    }

    @Override
    public ResponseEntity<MealsResponse> getMealsById(Long id) {
        Optional<MealEntity> meal = mealsService.getMealsById(id);
        return meal.map(MealsMapper::toMealsResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(long id) {
        Optional<Void> result = mealsService.deleteMeals(id);
        if(result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MealsResponse> createMeals(MealsRequest mealsRequest, UriComponentsBuilder uriBuilder) {
        Optional<MealEntity> meal = mealsService.createMeals(mealsRequest);
        var uri = uriBuilder.path("/{id}").buildAndExpand(meal.get().getId()).toUri();
        return ResponseEntity.created(uri).body(MealsMapper.toMealsResponse(meal.get()));
    }

    @Override
    public ResponseEntity<MealsResponse> updateMeals(MealsRequest mealsRequest, Long id) {
        Optional<MealEntity> meal = mealsService.updateMeals(id,mealsRequest);
        return meal.map(MealsMapper::toMealsResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
