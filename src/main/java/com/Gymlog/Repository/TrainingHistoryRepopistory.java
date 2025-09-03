package com.Gymlog.Repository;

import com.Gymlog.Entity.TrainingHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingHistoryRepopistory extends JpaRepository<TrainingHistoryEntity, Long> {
    Page<TrainingHistoryEntity> findAllByUserEntity_UserId(PageRequest of, Long id);
    List<TrainingHistoryEntity> findAllByUserEntity_UserId(Long id);
}
