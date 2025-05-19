package com.Gymlog.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class FoodEntity {
    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int calories;

    @Column(nullable = false)
    private int proteins;

    @Column(nullable = false)
    private int carbs;

    @Column(nullable = false)
    private int fibers;

    @Column(nullable = false)
    private int fats;

    @OneToMany(mappedBy = "food")
    private Set<MealItemEntity> mealItems;
}
