package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.FoodMapper;
import com.Gymlog.Controllers.Request.FoodRequest;
import com.Gymlog.Controllers.Response.FoodResponse;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Repository.FoodRepository;
import com.Gymlog.Service.FoodService;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping("/GymLog/Food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/")
    public ResponseEntity<List<FoodResponse>> getFoods(@RequestParam int page, @RequestParam int size) {
        List<FoodEntity> foods = foodService.getAllFoods();

        Stream foodResponseStream = foods.stream().map(FoodMapper::toFoodResponse);

        return ResponseEntity.ok(foodResponseStream.toList());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<FoodResponse> getFoodById(@PathVariable Long id) {
        Optional<FoodEntity> food = foodService.getFoodById(id);

        if(food.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FoodMapper.toFoodResponse(food.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponse> updateFood(@RequestBody FoodRequest foodRequest , @PathVariable Long id) {
        Optional<FoodEntity> food = foodService.updateFood(id,foodRequest);

        if(food.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(FoodMapper.toFoodResponse(food.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        Optional<FoodEntity> findFood = foodService.getFoodById(id);

        if(findFood.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        foodService.deleteFood(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byPage")
    public ResponseEntity<Page<FoodResponse>> getFoodsByPage(@RequestParam int page, @RequestParam int size) {
        Page<FoodEntity> foods = foodService.getAllFoodsByPage(page, size);
        Page<FoodResponse> foodResponseStream =  foods.map(FoodMapper::toFoodResponse);
        return ResponseEntity.ok().body(foodResponseStream);
    }


    @PostMapping("/")
    public  ResponseEntity<FoodResponse> saveFood(@RequestBody FoodRequest foodRequest, @NotNull UriComponentsBuilder uriBuilder) {
        FoodEntity foodEntity =  FoodMapper.toFoodEntity(foodRequest);
        FoodEntity savedFood = foodService.save(foodEntity);
        FoodResponse response = FoodMapper.toFoodResponse(savedFood);

        return  ResponseEntity.created(uriBuilder.path("/GymLog/Food/{id}").buildAndExpand(savedFood.getId()).toUri()).body(response);
    }

}
