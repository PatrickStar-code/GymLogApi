package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.MealItemMapper;
import com.Gymlog.Controllers.Request.MealItemRequest;
import com.Gymlog.Controllers.Response.MealItemResponse;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Entity.MealEntity;
import com.Gymlog.Entity.MealItemEntity;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Repository.FoodRepository;
import com.Gymlog.Repository.MealItemRepository;
import com.Gymlog.Repository.MealsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealItemService {

    private final MealItemRepository repository;
    private final FoodRepository foodRepository;
    private final MealsRepository mealsRepository;

    public List<MealItemEntity> getMealItem() {
        return repository.findAll();
    }

    public Optional<MealItemEntity> getMealItemById(Long id) {
        Optional<MealItemEntity> result = repository.findById(id);
        if(result.isPresent()) return result;
        return Optional.empty();
    }

    @Transactional
    public Optional<MealItemEntity> updateMealItem(MealItemRequest mealItemRequest, Long id) {
        Optional<MealItemEntity> result = repository.findById(id);

        if(result.isPresent()) {


            MealEntity mealEntity = getMeals(mealItemRequest.meals()).get();
            FoodEntity foodEntity = getFood(mealItemRequest.food()).get();

            if(mealEntity == null || foodEntity == null) return Optional.empty();

            MealItemEntity mealItemEntity = result.get();
            mealItemEntity.setMeal(mealEntity);
            mealItemEntity.setFood(foodEntity);
            mealItemEntity.setQuantity(mealItemRequest.quantity());
            repository.save(mealItemEntity);
            return Optional.of(mealItemEntity);
        }
        return Optional.empty();
    }


    @Transactional
    public Optional<MealItemEntity> createMealItem(MealItemRequest mealItemRequest) {
        Optional<MealEntity> mealEntityOptional = getMeals(mealItemRequest.meals());
        Optional<FoodEntity> foodEntityOptional = getFood(mealItemRequest.food());

        if (mealEntityOptional.isEmpty() || foodEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("O ID da refeição e do alimento não podem ser nulos.");
        }

        MealEntity mealEntity = mealEntityOptional.get();
        FoodEntity foodEntity = foodEntityOptional.get();

        MealItemEntity mealItemEntity = MealItemMapper.toEntity(mealItemRequest);
        mealItemEntity.setMeal(mealEntity);
        mealItemEntity.setFood(foodEntity);

        repository.save(mealItemEntity);
        return Optional.of(mealItemEntity);
    }

    @Transactional
    public void deleteMealItem(Long id) {
         repository.deleteById(id);
    }

    private Optional<MealEntity> getMeals(Long id) { return mealsRepository.findById(id);}
    private Optional<FoodEntity> getFood(Long id) { return foodRepository.findById(id);}


}
