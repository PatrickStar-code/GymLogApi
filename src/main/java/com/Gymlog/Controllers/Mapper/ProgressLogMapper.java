package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.ProgressLogRequest;
import com.Gymlog.Controllers.Response.ProgressLogResponse;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.ProgressLogEntity;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ProgressLogMapper {

    private static UserService userService;

    public static ProgressLogResponse toProgressLogResponse(ProgressLogEntity progressLogEntity) {

        return ProgressLogResponse.builder()
                .id(progressLogEntity.getId())
                .date(progressLogEntity.getDate())
                .weight(progressLogEntity.getWeight())
                .bodyFat(progressLogEntity.getBodyFat())
                .hip(progressLogEntity.getHip())
                .chest(progressLogEntity.getChest())
                .armsLeft(progressLogEntity.getArmsLeft())
                .armsRight(progressLogEntity.getArmsRight())
                .thighLeft(progressLogEntity.getThighLeft())
                .thighRight(progressLogEntity.getThighRight())
                .leftCalf(progressLogEntity.getLeftCalf())
                .rightCalf(progressLogEntity.getRightCalf())
                .waist(progressLogEntity.getWaist())
                .comment(progressLogEntity.getComment())
                .responsible(progressLogEntity.getResponsible())
                .user(progressLogEntity.getUser().getUserId())
                .build();
    }

    public static ProgressLogEntity toProgressEntity(ProgressLogRequest progressLogRequest) {

        Optional<UserEntity> user = userService.getUser(progressLogRequest.user());

        return ProgressLogEntity.builder()
                .comment(progressLogRequest.comment())
                .date(progressLogRequest.date())
                .weight(progressLogRequest.weight())
                .bodyFat(progressLogRequest.bodyFat())
                .hip(progressLogRequest.hip())
                .chest(progressLogRequest.chest())
                .armsLeft(progressLogRequest.armsLeft())
                .armsRight(progressLogRequest.armsRight())
                .thighLeft(progressLogRequest.thighLeft())
                .thighRight(progressLogRequest.thighRight())
                .leftCalf(progressLogRequest.leftCalf())
                .rightCalf(progressLogRequest.rightCalf())
                .waist(progressLogRequest.waist())
                .responsible(progressLogRequest.responsible())
                .user(user.get())
                .build();
    }
}
