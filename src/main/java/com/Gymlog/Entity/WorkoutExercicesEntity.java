package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "workout_exercices")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutExercicesEntity {
    @Id
    @Column(name = "workout_exercices_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "exercice_id")
    private String exerciceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlanEntity workoutPlan;


}
