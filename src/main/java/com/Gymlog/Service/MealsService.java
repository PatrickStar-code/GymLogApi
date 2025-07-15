package com.Gymlog.Service;

import com.Gymlog.Controllers.Mapper.MealsMapper;
import com.Gymlog.Controllers.Request.MealsRequest;
import com.Gymlog.Entity.MealEntity;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Repository.MealsRepository;
import com.Gymlog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealsService {

    private final MealsRepository mealsRepository;
    private final UserRepository userRepository;

    public List<MealEntity> getMeals() {
        return mealsRepository.findAll();
    }

    public Optional<MealEntity> getMealsById(Long id) {
        Optional<MealEntity> meal = mealsRepository.findById(id);
        if(meal.isPresent()) return meal;
        return Optional.empty();
    }


    public Optional<Void> deleteMeals(long id) {
        Optional<MealEntity> result = mealsRepository.findById(id);
        if(result.isPresent()) mealsRepository.delete(result.get());
        return Optional.empty();
    }

    public Optional<MealEntity> updateMeals(Long id, MealsRequest mealsRequest) {
        Optional<MealEntity> result = mealsRepository.findById(id);
        if(result.isPresent()) {
            MealEntity mealEntity = result.get();
            mealEntity.setDateTime(mealsRequest.dateTime());
            mealEntity.setMealType(mealsRequest.mealType());
            mealEntity.setCalories(mealsRequest.calories());
            mealEntity.setProteins(mealsRequest.proteins());
            mealEntity.setCarbs(mealsRequest.carbs());
            mealEntity.setFats(mealsRequest.fats());
            mealsRepository.save(mealEntity);
            return Optional.of(mealEntity);
        }
        return Optional.empty();
    }

    public Optional<MealEntity> createMeals(MealsRequest mealsRequest) {
        Optional<UserEntity> userEntity = userRepository.findById(mealsRequest.user());

        if (userEntity.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + mealsRequest.user());
        }

        MealEntity mealEntity = MealsMapper.toMealEntity(mealsRequest);
        mealEntity.setUser(userEntity.get());

        mealsRepository.save(mealEntity);
        return Optional.of(mealEntity);
    }

    public Page<MealEntity> getAllMealsByPage(int page, int size) {
        return mealsRepository.findAll(PageRequest.of(page, size));
    }
}
