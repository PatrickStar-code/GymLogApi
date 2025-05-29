package com.Gymlog.Controllers.Response;

import com.Gymlog.Enums.ActivyLevel;
import com.Gymlog.Enums.GenderEnum;
import com.Gymlog.Enums.Goal;
import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        Long userid,
        String username,
        @Email String email,
        LocalDateTime createdAt,
        GenderEnum gender,
        double height,
        double weight,
        int age,
        Goal goal,
        double goalWeight,
        LocalDateTime birthDate,
        ActivyLevel activyLevel,
        String avatarUrl,
        boolean isActive,
        LocalDateTime updatedAt
) {
}
