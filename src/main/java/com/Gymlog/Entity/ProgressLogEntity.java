package com.Gymlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "progress_log")
public class ProgressLogEntity {
    @Id
    @Column(name = "progress_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private double weight;

    @Column(name = "body_fat", nullable = false)
    private double bodyFat;

    @Column(nullable = false)
    private double hip;

    @Column(nullable = false)
    private double chest;

    @Column(name =  "arms_Left", nullable = false)
    private double armsLeft;

    @Column(name = "arms_right" ,nullable = false)
    private double armsRight;

    @Column(name = "thigh_left" ,nullable = false)
    private double thighLeft;

    @Column(name = "thigh_right" ,nullable = false)
    private double thighRight;

    @Column(nullable = false)
    private  double leftCalf;

    @Column(nullable = false)
    private double rightCalf;

    @Column(nullable = false)
    private String waist;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String responsible;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
