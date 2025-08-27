package com.Gymlog.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String foodName;

    @NotNull
    @Column(nullable = false)
    private double calories;

    @NotNull
    @Column(nullable = false)
    private double proteins;

    @NotNull
    @Column(nullable = false)
    private double carbs;

    @NotNull
    @Column(nullable = false)
    private double fibers;

    @NotNull
    @Column(nullable = false)
    private double fats;

    @NotNull
    @Column(nullable = false)
    private double grams;

}
