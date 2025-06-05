package com.Gymlog.Controllers.Request;

import lombok.Builder;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record ProgressLogRequest(
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
        double waist,
        String comment,
        String responsible,
        Long user
) {
}
