package com.Gymlog.Repository;

import com.Gymlog.Entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealsRepository extends JpaRepository<MealEntity, Long> {
}
