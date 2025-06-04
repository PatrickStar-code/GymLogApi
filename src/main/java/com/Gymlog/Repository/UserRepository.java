package com.Gymlog.Repository;

import com.Gymlog.Entity.UserEntity;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailIgnoreCaseAndVerifiedTrue(String email);

    Optional<UserEntity> findByVerificationToken(String code);

    Optional<UserEntity> findByEmailIgnoreCase(@Email String email);
}
