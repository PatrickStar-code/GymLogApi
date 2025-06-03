package com.Gymlog.Controllers.Request;

import com.Gymlog.Enums.ActivyLevel;
import com.Gymlog.Enums.Goal;
import jakarta.validation.constraints.Email;

public record UpdateRequest
        (
                String username,
                double weight,
                double height,
                double goalWeight,
                Goal goal,
                String avatarUrl,
                ActivyLevel activyLevel

        ){
}
