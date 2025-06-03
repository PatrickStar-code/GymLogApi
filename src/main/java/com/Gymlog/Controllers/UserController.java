package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.UserMapper;
import com.Gymlog.Controllers.Request.UpdatePassword;
import com.Gymlog.Controllers.Request.UpdateRequest;
import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest, @NotNull UriComponentsBuilder uriBuilder){
        UserEntity usuario = userService.registerUser(userRequest);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(usuario.getUserId()).toUri();
        return ResponseEntity.created(uri).body(UserMapper.toUserResponse(usuario));
    }

    @PutMapping("/users/{id}/is-active")
    public ResponseEntity<UserResponse> updateIsActive(@PathVariable Long id) {
        Optional<UserEntity> user = userService.updateIsActive(id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody UpdatePassword updatePassword , @PathVariable Long id) {
        Optional<UserEntity> user = userService.updatePassword(updatePassword, id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateRequest userRequest , @PathVariable Long id){
        Optional<UserEntity> user = userService.updateUser(userRequest, id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        Optional<UserEntity> user = userService.getUser(id);
        return user.map(UserMapper::toUserResponse).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/verify-user")
        public ResponseEntity<String> verifyEmail(@RequestParam(value = "code") String code) {
            userService.verifyEmail(code);
            return ResponseEntity.ok("User verified with success");
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity<Void> delete(@PathVariable long id){
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }


}




