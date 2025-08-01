package com.Gymlog.Entity;

import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Enums.ActivyLevel;
import com.Gymlog.Enums.GenderEnum;
import com.Gymlog.Enums.Goal;
import com.Gymlog.Enums.RolesEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(  nullable = false)
    private String username;

    @Column( nullable = false,unique = true)
    @Email
    private String email;

    @Column( nullable = false)
    private String password;

    @Column( nullable = false)
    private LocalDateTime createdAt;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column( nullable = false)
    private double height;

    @Column( nullable = false)
    private double weight;

    @Column( nullable = false)
    private int age;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column( nullable = false)
    private double goalWeight;

    @Column( nullable = false)
    private  LocalDateTime birthDate;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivyLevel activyLevel;

    @Column(name = "avatar_url" , nullable = false)
    private String avatarUrl;

    @Column( name = "is_active" , nullable = false)
    private boolean isActive;

    @Column( name = "updated_at" )
    private LocalDateTime updatedAt;

    @Column( name = "deleted_at" )
    private LocalDateTime deletedAt;

    private  Boolean verified;

    @Column( name = "verification_token")
    private String verificationToken;

    @Column( name = "verification_token_expiration_date")
    private LocalDateTime tokenExpirationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProgressLogEntity> progressLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<WorkoutPlanEntity> workoutPlans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MealEntity> meals;



    public UserEntity(@Valid UserRequest dados, String passwordEncrypted) {
        this.username = dados.username();
        this.email = dados.email();
        this.password = passwordEncrypted;
        this.createdAt = LocalDateTime.now();
        this.gender = dados.gender();
        this.height = dados.height();
        this.weight = dados.weight();
        this.age = dados.age();
        this.goal = dados.goal();
        this.goalWeight = dados.goalWeight();
        this.birthDate = dados.birthDate();
        this.activyLevel = dados.activyLevel();
        this.avatarUrl = dados.avatarUrl();
        this.isActive = true;
        this.updatedAt = null;
        this.deletedAt = null;
        this.verified = false;
        this.verificationToken = UUID.randomUUID().toString();
        this.role = dados.role() == null ? RolesEnum.USER : RolesEnum.valueOf(dados.role());
        this.tokenExpirationDate = LocalDateTime.now().plusMinutes(30);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == RolesEnum.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        }
        else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public void verify() {
        if(tokenExpirationDate.isBefore(LocalDateTime.now())){
            throw  new RuntimeException("Token de verificação expirou!");
        };
        this.verified = true;
        this.verificationToken = null;
        this.tokenExpirationDate = null;
    }


}
