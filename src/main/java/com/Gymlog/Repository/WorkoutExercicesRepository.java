package com.Gymlog.Repository;

import com.Gymlog.Entity.WorkoutExercicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExercicesRepository extends JpaRepository<WorkoutExercicesEntity,Long> {
}
