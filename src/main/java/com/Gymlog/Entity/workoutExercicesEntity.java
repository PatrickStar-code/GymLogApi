package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "workout_exercices")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class workoutExercicesEntity {
    @Id
    @Column(name = "workout_exercices_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "exercice_id")
    private String exerciceId;

    @Column(nullable = false)
    private  int sets;

    @Column(nullable = false)
    private int reps;

    @Column(nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlanEntity workoutPlan;


}
