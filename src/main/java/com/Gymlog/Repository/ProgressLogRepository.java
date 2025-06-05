package com.Gymlog.Repository;

import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressLogRepository extends JpaRepository<ProgressLogEntity, Long> {
    Optional<List<ProgressLogEntity>> findByUser(UserEntity user);
}
