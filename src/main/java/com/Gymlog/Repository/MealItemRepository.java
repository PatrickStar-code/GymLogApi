package com.Gymlog.Repository;

import com.Gymlog.Entity.MealItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealItemRepository extends JpaRepository<MealItemEntity, Long> {
}
