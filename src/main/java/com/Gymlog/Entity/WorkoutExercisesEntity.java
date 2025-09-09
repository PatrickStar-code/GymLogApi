package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "workout_exercices")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutExercisesEntity {
    @Id
    @Column(name = "workout_exercices_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "exercice_id")
    private String exerciceId;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlanEntity workoutPlan;

    @OneToMany(mappedBy = "workoutExercises")
    private Set<WorkoutSetsEntity> workoutSets;

}
