package com.Gymlog.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private LocalDateTime  ocurrenceDate;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlanEntity workoutPlanEntity;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
