package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.MealItemMapper;
import com.Gymlog.Controllers.Request.MealItemRequest;
import com.Gymlog.Controllers.Response.MealItemResponse;
import com.Gymlog.Controllers.SwaggerInterface.MealItemControllerInterface;
import com.Gymlog.Entity.MealItemEntity;
import com.Gymlog.Service.MealItemService;
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
@RequestMapping("/GymLog/mealItem")
@RequiredArgsConstructor
public class MealItemController implements MealItemControllerInterface {

    private final MealItemService service;


    @Override
    public ResponseEntity<Page<MealItemResponse>> getMealItem(int page, int size) {
        if(page >= 0 && size > 0) {
            Page<MealItemEntity> mealItems = service.getAllMealItemsByPage(page, size);
            Page<MealItemResponse> mealItemResponseStream =  mealItems.map(MealItemMapper::toResponse);
            return ResponseEntity.ok().body(mealItemResponseStream);
        }
        List<MealItemEntity> mealItems = service.getMealItem();
        Stream<MealItemResponse> mealItemResponseStream = mealItems.stream().map(MealItemMapper::toResponse);
        return ResponseEntity.ok(new PageImpl<>(mealItemResponseStream.toList(), PageRequest.of(0,mealItems.size() > 0 ? mealItems.size() : 1),mealItems.size() > 0 ? mealItems.size() : 1));
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
        System.out.println(mealItemRequest);
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
