package com.Gymlog.Config;

import com.Gymlog.Handler.CustomAcessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final FilterTokenJWT filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAcessDeniedHandler accessDeniedHandler) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/api/api-docs/**","/swagger/**","/login", "/refresh-token", "GymLog/users/register","GymLog/users/verify-user").permitAll()

                        //User
                        // /Food
                        .requestMatchers("/GymLog/Food/","GET").hasRole("USER")
                        .requestMatchers("/GymLog/Food/{id}","GET").hasRole("USER")
                        .requestMatchers("/GymLog/users/{id}","GET").hasRole("USER")


                        // /Admin
                        // /User
                        .requestMatchers("/GymLog/users/{id}","DELETE").hasRole("ADMIN")


                        .anyRequest().authenticated()   )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }


    @Bean
    public PasswordEncoder encriptador(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
