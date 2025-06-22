package com.Gymlog.Config;

import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Repository.UserRepository;
import com.Gymlog.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterTokenJWT  extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenRequisition(request);

            if (token != null) {
                String email = tokenService.verifyToken(token);
                UserEntity user = userRepository.findByEmailIgnoreCaseAndVerifiedTrue(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou não verificado"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            // Limpa o contexto de segurança para evitar problemas posteriores
            SecurityContextHolder.clearContext();

            // Define o status HTTP e o conteúdo da resposta
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.setContentType("application/json");

            // Monta uma resposta JSON simples
            String json = "{\"error\": \"" + ex.getMessage().replace("\"", "'") + "\"}";

            response.getWriter().write(json);
            response.getWriter().flush();
        }
    }


    private String getTokenRequisition(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
