package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workout_sets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSetsEntity {
    @Id
    @Column(name = "workout_sets_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int reps;

    @Column(nullable = false)
    private int sets;

    @Column(nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_exercices_id")
    private WorkoutExercisesEntity workoutExercises;
}
