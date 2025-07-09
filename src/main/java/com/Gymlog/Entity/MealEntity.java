package com.Gymlog.Entity;

import com.Gymlog.Enums.MealType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meals")
@Entity
@Builder
public class MealEntity {
    @Id
    @Column(name = "meal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MealType mealType;


    @Column(nullable = false)
    private double calories;


    @Column(nullable = false)
    private double proteins;


    @Column(nullable = false)
    private double carbs;



    @Column(nullable = false)
    private double fats;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MealItemEntity> mealItems;

}
