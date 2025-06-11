package com.Gymlog.Repository;

import com.Gymlog.Entity.WorkoutSetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutSetsRepository extends JpaRepository<WorkoutSetsEntity, Integer> {
}
