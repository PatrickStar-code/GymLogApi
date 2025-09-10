package com.Gymlog.Repository;

import com.Gymlog.Entity.MealEntity;
import com.Gymlog.Entity.MealItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealItemRepository extends JpaRepository<MealItemEntity, Long> {
    Page<MealItemEntity> findByMeal_Id(Long mealId, Pageable pageable);
    List<MealItemEntity> findByMeal_Id(Long mealId);
    boolean existsByMealId(Long mealId);
}
