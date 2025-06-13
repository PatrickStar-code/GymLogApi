package com.Gymlog.Service;

import com.Gymlog.Entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
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

    public String generateToken(UserEntity userEntity) {

            try {
                Algorithm algorithm = Algorithm.HMAC256("152546");
                return JWT.create()
                        .withIssuer("GymLog")
                        .withSubject(userEntity.getEmail())
                        .withExpiresAt(expirationDate(30))
                        .sign(algorithm);
            } catch (JWTCreationException exception) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao gerar token JWT de acesso!");
            }

    }

    public String generateRefreshToken(UserEntity userEntity) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("152546");
            return JWT.create()
                    .withIssuer("GymLog")
                    .withSubject(userEntity.getUserId().toString())
                    .withExpiresAt(expirationDate(120))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao gerar token JWT de Refresh!");
        }

    }

    public String verifyToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("152546");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("GymLog")
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido!");
        }
    }



    private Instant expirationDate(Integer i) {
        return LocalDateTime.now().plusMinutes(i).toInstant(ZoneOffset.of("-03:00"));
    }

}
