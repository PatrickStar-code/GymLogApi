package com.Gymlog.Controllers.Response;

import lombok.Builder;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Builder
public record ProgressLogResponse(
        Long id,
        LocalDateTime date,
        double weight,
        double bodyFat,
        double hip,
        double chest,
        double armsLeft,
        double armsRight,
        double thighLeft,
        double thighRight,
        double leftCalf,
        double rightCalf,
        String waist,
        String comment,
        String responsible,
        Long user
) {
}
