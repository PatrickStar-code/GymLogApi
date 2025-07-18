package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.FoodMapper;
import com.Gymlog.Controllers.Request.FoodRequest;
import com.Gymlog.Controllers.Response.FoodResponse;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Service.FoodService;
import com.Gymlog.Controllers.SwaggerInterface.FoodControllerInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/GymLog/Food")
@RequiredArgsConstructor
@Tag(
        name = "Food",
        description = "Endpoints para gerenciamento de alimentos"
)
public class FoodController implements FoodControllerInterface {

    private final FoodService foodService;

    @Override
    public ResponseEntity<Page<FoodResponse>> getFoods(int page, int size) {

        if(page >= 0 && size > 0) {
            Page<FoodEntity> foods = foodService.getAllFoodsByPage(page, size);
            Page<FoodResponse> foodResponseStream =  foods.map(FoodMapper::toFoodResponse);
            return ResponseEntity.ok().body(foodResponseStream);
        }

        List<FoodEntity> foods = foodService.getAllFoods();

        Stream<FoodResponse> foodResponseStream = foods.stream().map(FoodMapper::toFoodResponse);
        PageImpl<FoodResponse> foodResponsePage = new PageImpl<>(foodResponseStream.toList(), PageRequest.of(0,foods.size() > 0 ? foods.size() : 1),foods.size() > 0 ? foods.size() : 1);
        return ResponseEntity.ok(foodResponsePage);

    }

    @Override
    public ResponseEntity<FoodResponse> getFoodById(Long id) {
        Optional<FoodEntity> food = foodService.getFoodById(id);

        if(food.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FoodMapper.toFoodResponse(food.get()));    }

    @Override
    public ResponseEntity<FoodResponse> updateFood(FoodRequest foodRequest, Long id) {
        Optional<FoodEntity> food = foodService.updateFood(id,foodRequest);

        if(food.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(FoodMapper.toFoodResponse(food.get()));

    }

    @Override
    public ResponseEntity<FoodResponse> saveFood(FoodRequest foodRequest, UriComponentsBuilder uriBuilder) {
        FoodEntity foodEntity =  FoodMapper.toFoodEntity(foodRequest);
        FoodEntity savedFood = foodService.save(foodEntity);
        FoodResponse response = FoodMapper.toFoodResponse(savedFood);

        return  ResponseEntity.created(uriBuilder.path("/GymLog/Food/{id}").buildAndExpand(savedFood.getId()).toUri()).body(response);    }


    @Override
    public ResponseEntity<Void> delete(long id) {
        Optional<FoodEntity> findFood = foodService.getFoodById(id);

        if(findFood.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        foodService.deleteFood(id);
        return ResponseEntity.ok().build();
    }
}
