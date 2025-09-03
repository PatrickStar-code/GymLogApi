package com.Gymlog.Repository;

import com.Gymlog.Entity.TrainingHistoryEntity;
import com.Gymlog.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingHistoryRepopistory extends JpaRepository<TrainingHistoryEntity, Long> {
    Page<TrainingHistoryEntity> findAllByUserEntity_UserId(PageRequest of, Long id);
    List<TrainingHistoryEntity> findAllByUserEntity_UserId(Long id);

    @Query(value = "SELECT * FROM training_history WHERE user_id = ?1 AND ocurrence_date >= make_date(?3, ?2, 1) AND ocurrence_date < make_date(?3, ?2, 1) + interval '1 month'",  nativeQuery = true)
    Page<TrainingHistoryEntity> findAllByUserEntity_UserIdAndByMonthAndByYear(PageRequest of, Long UserId, int month, int year);

    @Query(value = "SELECT * FROM training_history WHERE ocurrence_date >= make_date(?2, ?1, 1) AND ocurrence_date < make_date(?2, ?1, 1) + interval '1 month'",  nativeQuery = true)
    List<TrainingHistoryEntity> findAllByByMonthAndByYear(int month, int year);
}
