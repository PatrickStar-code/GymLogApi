package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workout_plan")
public class WorkoutPlanEntity {
    @Id
    @Column(name = "workout_plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany
    private Set<workoutExercicesEntity> workoutExercices;

    @OneToMany
    private Set<WorkoutSetsEntity> workoutSets;


}
