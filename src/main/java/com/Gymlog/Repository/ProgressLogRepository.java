package com.Gymlog.Repository;

import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressLogRepository extends JpaRepository<ProgressLogEntity, Long> {
    List<ProgressLogEntity> findByUser(UserEntity user);
    Page<ProgressLogEntity> findByUser(UserEntity user, PageRequest pageRequest);
}
