package com.Gymlog.Service;

import com.Gymlog.Controllers.Request.FoodRequest;
import com.Gymlog.Controllers.Response.FoodResponse;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;



    public List<FoodEntity> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<FoodEntity> getFoodById(Long id){
        return  foodRepository.findById(id);
    }

    public  Optional<FoodEntity> updateFood(Long id, FoodRequest foodRequest) {
        Optional<FoodEntity> food = foodRepository.findById(id);
        if (food.isPresent()) {

            FoodEntity foodEntity = food.get();

            foodEntity.setFoodName(foodRequest.foodName());
            foodEntity.setCalories(foodRequest.calories());
            foodEntity.setProteins(foodRequest.proteins());
            foodEntity.setCarbs(foodRequest.carbs());
            foodEntity.setFibers(foodRequest.fibers());
            foodEntity.setFats(foodRequest.fats());
            foodRepository.save(foodEntity);

            return Optional.of(foodEntity);
        }
        return Optional.empty();
    }

    public FoodEntity save(FoodEntity foodEntity) {
        return foodRepository.save(foodEntity);
    }

    public void deleteFood(long id) {
        foodRepository.deleteById(id);
    }

    public Page<FoodEntity> getAllFoodsByPage(int page, int size) {
        return foodRepository.findAll(PageRequest.of(page, size));
    }
}
