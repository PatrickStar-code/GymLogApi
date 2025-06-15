package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.MealItemMapper;
import com.Gymlog.Controllers.Request.MealItemRequest;
import com.Gymlog.Controllers.Response.MealItemResponse;
import com.Gymlog.Entity.MealItemEntity;
import com.Gymlog.Service.MealItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/mealItem")
@RequiredArgsConstructor
public class MealItemController {

    private final MealItemService service;

    @GetMapping("/")
    public ResponseEntity<List<MealItemResponse>> getMealItem() {
        List<MealItemEntity> mealItemEntity = service.getMealItem();
        List<MealItemResponse> mealItemResponse = mealItemEntity.stream().map(MealItemMapper::toResponse).toList();
        return ResponseEntity.ok(mealItemResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealItemResponse> getMealItemById(@PathVariable Long id) {
        Optional<MealItemEntity> mealItem = service.getMealItemById(id);
        if(mealItem.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(MealItemMapper.toResponse(mealItem.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealItemResponse> updateMealItem(@RequestBody MealItemRequest mealItemRequest , @PathVariable Long id) {
        Optional<MealItemEntity> mealItem = service.updateMealItem(mealItemRequest, id);
        return mealItem.map(MealItemMapper::toResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<MealItemResponse> createMealItem(@RequestBody MealItemRequest mealItemRequest) {
        Optional<MealItemEntity> mealItem = service.createMealItem(mealItemRequest);
        return mealItem.map(MealItemMapper::toResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(400).build());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteMealItem(@PathVariable Long id) {
        Optional<MealItemEntity> mealItem = service.getMealItemById(id);
        if(mealItem.isEmpty()) return ResponseEntity.notFound().build();
        service.deleteMealItem(id);
        return ResponseEntity.noContent().build();
    }

}
