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

import java.util.List;
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
         var html = "<!DOCTYPE html>\n" +
                 "<html lang=\"en\">\n" +
                 "<head>\n" +
                 "  <meta charset=\"UTF-8\">\n" +
                 "  <title>User Verified</title>\n" +
                 "  <style>\n" +
                 "    body {\n" +
                 "      background: linear-gradient(135deg, #4CAF50, #2E7D32);\n" +
                 "      font-family: Arial, sans-serif;\n" +
                 "      display: flex;\n" +
                 "      justify-content: center;\n" +
                 "      align-items: center;\n" +
                 "      height: 100vh;\n" +
                 "      margin: 0;\n" +
                 "    }\n" +
                 "\n" +
                 "    .container {\n" +
                 "      background-color: #ffffffdd;\n" +
                 "      padding: 40px 60px;\n" +
                 "      border-radius: 12px;\n" +
                 "      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.25);\n" +
                 "      text-align: center;\n" +
                 "    }\n" +
                 "\n" +
                 "    h1 {\n" +
                 "      color: #2E7D32;\n" +
                 "      font-size: 32px;\n" +
                 "      margin-bottom: 20px;\n" +
                 "    }\n" +
                 "\n" +
                 "    p {\n" +
                 "      font-size: 18px;\n" +
                 "      color: #333;\n" +
                 "    }\n" +
                 "\n" +
                 "    .checkmark {\n" +
                 "      font-size: 60px;\n" +
                 "      color: #4CAF50;\n" +
                 "      margin-bottom: 20px;\n" +
                 "    }\n" +
                 "  </style>\n" +
                 "</head>\n" +
                 "<body>\n" +
                 "  <div class=\"container\">\n" +
                 "    <div class=\"checkmark\">✔\uFE0F</div>\n" +
                 "    <h1>User Verified</h1>\n" +
                 "    <p>User verified with success</p>\n" +
                 "  </div>\n" +
                 "</body>\n" +
                 "</html>";
        return ResponseEntity.ok(html);
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

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        List<UserResponse> response = users.stream().map(UserMapper::toUserResponse).toList();
        return ResponseEntity.ok(response);    }


}




