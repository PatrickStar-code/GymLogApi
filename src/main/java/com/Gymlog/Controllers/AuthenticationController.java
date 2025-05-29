package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Request.LoginUser;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Records.RefreshTokenData;
import com.Gymlog.Records.TokenData;
import com.Gymlog.Repository.UserRepository;
import com.Gymlog.Service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<TokenData> Login(@Valid @RequestBody LoginUser user){
        var authentication = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        var Token = authenticationManager.authenticate(authentication);

        String TokenJwt =tokenService.generateToken((UserEntity) Token.getPrincipal());
        String refreshJwt = tokenService.generateRefreshToken((UserEntity) Token.getPrincipal());

        return ResponseEntity.ok(new TokenData (TokenJwt, refreshJwt));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenData> refreshToken(@Valid @RequestBody RefreshTokenData user){
        var refreshToken = user.refreshToken();
        Long userId = Long.valueOf(tokenService.verifyToken(refreshToken));
        UserEntity userFinded = userRepository.findById(userId).orElseThrow();

        String TokenJwt =tokenService.generateToken(userFinded);
        String refreshJwt = tokenService.generateRefreshToken(userFinded);

        return ResponseEntity.ok(new TokenData (TokenJwt, refreshJwt));
    }

}
