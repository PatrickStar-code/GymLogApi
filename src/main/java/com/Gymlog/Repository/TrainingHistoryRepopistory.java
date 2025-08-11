package com.Gymlog.Repository;

import com.Gymlog.Entity.TrainingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingHistoryRepopistory extends JpaRepository<TrainingHistoryEntity, Long> {

}
