package com.Gymlog.Controllers.Mapper;

import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Enums.RolesEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserResponse toUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .createdAt(userEntity.getCreatedAt())
                .gender(userEntity.getGender())
                .height(userEntity.getHeight())
                .weight(userEntity.getWeight())
                .age(userEntity.getAge())
                .goal(userEntity.getGoal())
                .goalWeight(userEntity.getGoalWeight())
                .birthDate(userEntity.getBirthDate())
                .activyLevel(userEntity.getActivyLevel())
                .avatarUrl(userEntity.getAvatarUrl())
                .isActive(userEntity.isActive())
                .updatedAt(userEntity.getUpdatedAt())
                .role(userEntity.getRole().name())
                .build();
    }

    public  static  UserEntity toUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .username(userRequest.username())
                .email(userRequest.email())
                .password(userRequest.password())
                .createdAt(userRequest.createdAt())
                .gender(userRequest.gender())
                .height(userRequest.height())
                .weight(userRequest.weight())
                .age(userRequest.age())
                .goal(userRequest.goal())
                .goalWeight(userRequest.goalWeight())
                .birthDate(userRequest.birthDate())
                .activyLevel(userRequest.activyLevel())
                .avatarUrl(userRequest.avatarUrl())
                .isActive(userRequest.isActive())
                .updatedAt(userRequest.updatedAt())
                .role(RolesEnum.valueOf(userRequest.role()) == null ? RolesEnum.USER : RolesEnum.valueOf(userRequest.role()))
                .build();
    }
}
