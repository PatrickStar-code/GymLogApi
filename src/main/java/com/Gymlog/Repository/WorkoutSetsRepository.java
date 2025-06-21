package com.Gymlog.Repository;

import com.Gymlog.Entity.WorkoutExercisesEntity;
import com.Gymlog.Entity.WorkoutSetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutSetsRepository extends JpaRepository<WorkoutSetsEntity, Long> {

    List<WorkoutSetsEntity> findByWorkoutExercisesId(Long id);
}
