package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Mapper.UserMapper;
import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Controllers.Response.UserResponse;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/verify-user")
        public ResponseEntity<String> verifyEmail(@RequestParam(value = "code") String code) {
            userService.verifyEmail(code);
            return ResponseEntity.ok("User verified with success");
        }
    }



