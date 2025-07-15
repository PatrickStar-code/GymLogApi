package com.Gymlog.Repository;

import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Entity.WorkoutPlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity,Long> {
    List<WorkoutPlanEntity> findByUser(UserEntity user);
    Page<WorkoutPlanEntity> findByUser(UserEntity user, PageRequest pageRequest);
}
