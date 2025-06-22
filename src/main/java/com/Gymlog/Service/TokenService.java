package com.Gymlog.Service;

import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Exceptions.InvalidTokenException;
import com.Gymlog.Exceptions.TokenGenerationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service

public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    private final Integer accessTokenExpiration = 30;

    private final Integer refreshTokenExpiration = 120;

    public String generateToken(UserEntity userEntity) {

            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.create()
                        .withIssuer(issuer)
                        .withSubject(userEntity.getEmail())
                        .withExpiresAt(expirationDate(accessTokenExpiration))
                        .sign(algorithm);
            } catch (JWTCreationException exception) {
                throw new TokenGenerationException("TOKEN_GENERATION_ERROR", "Erro ao gerar token JWT de refresh!");
            }

    }

    public String generateRefreshToken(UserEntity userEntity) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(userEntity.getUserId().toString())
                    .withExpiresAt(expirationDate(refreshTokenExpiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("TOKEN_GENERATION_ERROR", "Erro ao gerar token JWT de refresh!");
        }

    }

    public String verifyToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new InvalidTokenException("INVALID_TOKEN", "Token invalido!");
        }
    }



    private Instant expirationDate(Integer i) {
        return LocalDateTime.now().plusMinutes(i).toInstant(ZoneOffset.of("-03:00"));
    }

}
