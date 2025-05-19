package com.Gymlog.Entity;

import com.Gymlog.Enums.ActivyLevel;
import com.Gymlog.Enums.GenderEnum;
import com.Gymlog.Enums.Goal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(  nullable = false)
    private String username;

    @Column( nullable = false,unique = true)
    private String email;

    @Column( nullable = false)
    private String password;


    @Column( nullable = false)
    private LocalDateTime createdAt;

    @Column( nullable = false)
    private GenderEnum Gender;

    @Column( nullable = false)
    private double height;

    @Column( nullable = false)
    private double weight;

    @Column( nullable = false)
    private int age;

    @Column( nullable = false)
    private Goal goal;

    @Column( nullable = false)
    private double goalWeight;

    @Column( nullable = false)
    private  LocalDateTime birthDate;

    @Column( nullable = false)
    private ActivyLevel activyLevel;

    @Column(name = "avatar_url" , nullable = false)
    private String avatarUrl;

    @Column( name = "is_active" , nullable = false)
    private boolean isActive;

    @Column( name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt;

    @Column( name = "deleted_at" , nullable = false)
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user")
    private Set<ProgressLogEntity> progressLogs;

    @OneToMany(mappedBy = "user")
    private Set<WorkoutPlanEntity> workoutPlans;

    @OneToMany(mappedBy = "user")
    private Set<MealEntity> meals;

}
