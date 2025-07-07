package com.Gymlog.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String foodName;

    @NotBlank
    @Column(nullable = false)
    private double calories;

    @NotBlank
    @Column(nullable = false)
    private double proteins;

    @NotBlank
    @Column(nullable = false)
    private double carbs;

    @NotBlank
    @Column(nullable = false)
    private double fibers;

    @NotBlank
    @Column(nullable = false)
    private double fats;

    @NotBlank
    @Column(nullable = false)
    private double grams;

}
