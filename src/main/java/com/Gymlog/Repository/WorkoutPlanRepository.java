package com.Gymlog.Repository;

import com.Gymlog.Entity.WorkoutPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity,Long> {
}
