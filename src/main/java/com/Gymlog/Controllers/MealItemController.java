package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.MealItemMapper;
import com.Gymlog.Controllers.Request.MealItemRequest;
import com.Gymlog.Controllers.Response.MealItemResponse;
import com.Gymlog.Controllers.SwaggerInterface.MealItemControllerInterface;
import com.Gymlog.Entity.MealItemEntity;
import com.Gymlog.Service.MealItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GymLog/mealItem")
@RequiredArgsConstructor
public class MealItemController implements MealItemControllerInterface {

    private final MealItemService service;


    @Override
    public ResponseEntity<List<MealItemResponse>> getMealItem() {
        List<MealItemEntity> mealItemEntity = service.getMealItem();
        List<MealItemResponse> mealItemResponse = mealItemEntity.stream().map(MealItemMapper::toResponse).toList();
        return ResponseEntity.ok(mealItemResponse);
    }

    @Override
    public ResponseEntity<MealItemResponse> getMealItemById(Long id) {
        Optional<MealItemEntity> mealItem = service.getMealItemById(id);
        if(mealItem.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(MealItemMapper.toResponse(mealItem.get()));
    }

    @Override
    public ResponseEntity<MealItemResponse> updateMealItem(MealItemRequest mealItemRequest, Long id) {
        Optional<MealItemEntity> mealItem = service.updateMealItem(mealItemRequest, id);
        return mealItem.map(MealItemMapper::toResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<MealItemResponse> createMealItem(MealItemRequest mealItemRequest, UriComponentsBuilder uriBuilder) {
        Optional<MealItemEntity> mealItem = service.createMealItem(mealItemRequest);
        var uri = uriBuilder.path("/{id}").buildAndExpand(mealItem.get().getId()).toUri();
        return ResponseEntity.created(uri).body(MealItemMapper.toResponse(mealItem.get()));
    }

    @Override
    public ResponseEntity<Void> deleteMealItem(Long id) {
        Optional<MealItemEntity> mealItem = service.getMealItemById(id);
        if(mealItem.isEmpty()) return ResponseEntity.notFound().build();
        service.deleteMealItem(id);
        return ResponseEntity.noContent().build();
    }
}
