package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.UserMapper;
import com.Gymlog.Controllers.Request.UpdatePassword;
import com.Gymlog.Controllers.Request.UpdateRequest;
import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Service.UserService;
import com.Gymlog.Controllers.SwaggerInterface.UserControllerInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("GymLog/users")
@Tag(name = "User"
    , description = "User endpoints")
@SecurityRequirement(name = "bearerAuth")
public class UserController implements UserControllerInterface {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserRequest userRequest, UriComponentsBuilder uriBuilder) {
        UserEntity usuario = userService.registerUser(userRequest);
        var uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getUserId()).toUri();
        return ResponseEntity.created(uri).body(UserMapper.toUserResponse(usuario));
    }

    @Override
    public ResponseEntity<UserResponse> updateIsActive(Long id) {
        Optional<UserEntity> user = userService.updateIsActive(id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserResponse> updatePassword(UpdatePassword updatePassword, Long id) {
        Optional<UserEntity> user = userService.updatePassword(updatePassword, id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UpdateRequest userRequest, Long id) {
        Optional<UserEntity> user = userService.updateUser(userRequest, id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<String> verifyEmail(String code) {
        userService.verifyEmail(code);
        return ResponseEntity.ok("User verified with success");
    }

    @Override
    public ResponseEntity<Void> delete(long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserResponse> getUser(Long id) {
        Optional<UserEntity> user = userService.getUser(id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}




