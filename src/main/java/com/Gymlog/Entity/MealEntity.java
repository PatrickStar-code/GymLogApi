package com.Gymlog.Entity;

import com.Gymlog.Enums.MealType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meals")
@Entity
public class MealEntity {
    @Id
    @Column(name = "meal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private MealType mealType;

    @Column(nullable = false)
    private int calories;

    @Column(nullable = false)
    private int proteins;

    @Column(nullable = false)
    private int carbs;

    @Column(nullable = false)
    private int fats;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany
    private Set<MealItemEntity> mealItems;
}
