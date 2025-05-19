package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meal_item")
@Entity
public class MealItemEntity {
    @Id
    @Column(name = "meal_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

}

