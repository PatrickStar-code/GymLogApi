package com.Gymlog.Controllers;

import com.Gymlog.Controllers.Request.LoginUser;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Records.RefreshTokenData;
import com.Gymlog.Records.TokenData;
import com.Gymlog.Repository.UserRepository;
import com.Gymlog.Service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(
        name = "Autenticação",
        description = "Endpoints responsáveis pela autenticação de usuários e renovação de tokens"
)

public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Operation(
            summary = "Login do usuário",
            description = "Realiza o login de um usuário com email e senha. Retorna um token JWT e um refresh token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = TokenData.class))),
                    @ApiResponse(responseCode = "400", description = "Credenciais inválidas ou erro na autenticação",content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginUser user){
        try {
            var authentication = new UsernamePasswordAuthenticationToken(user.email(), user.password());
            var Token = authenticationManager.authenticate(authentication);

            String TokenJwt =tokenService.generateToken((UserEntity) Token.getPrincipal());
            String refreshJwt = tokenService.generateRefreshToken((UserEntity) Token.getPrincipal());

            return ResponseEntity.ok(new TokenData (TokenJwt, refreshJwt));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @Operation(
            summary = "Renovar token JWT",
            description = "Gera um novo token JWT e um novo refresh token a partir de um refresh token válido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Token renovado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Refresh token inválido ou expirado")
            }
    )
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
