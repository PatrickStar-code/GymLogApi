package com.Gymlog.Repository;

import com.Gymlog.Entity.ProgressLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressLogRepository extends JpaRepository<ProgressLogEntity, Long> {
}
