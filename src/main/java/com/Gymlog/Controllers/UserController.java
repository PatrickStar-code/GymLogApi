package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.UserMapper;
import com.Gymlog.Controllers.Request.UpdatePassword;
import com.Gymlog.Controllers.Request.UpdateRequest;
import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Service.UserService;
import com.Gymlog.SwaggerInterface.UserControllerInterface;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("GymLog/users")
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




