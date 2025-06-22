package com.Gymlog.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
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
    private double calories;

    @Column(nullable = false)
    private double proteins;

    @Column(nullable = false)
    private double carbs;

    @Column(nullable = false)
    private double fibers;

    @Column(nullable = false)
    private double fats;

    @Column(nullable = false)
    private double grams;

}
