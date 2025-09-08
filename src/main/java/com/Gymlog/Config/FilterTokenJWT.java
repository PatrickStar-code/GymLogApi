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
import org.springframework.dao.DataIntegrityViolationException;

@Component
@RequiredArgsConstructor
public class FilterTokenJWT  extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals("/login") || uri.equals("/refresh-token") || uri.equals("/GymLog/users/register") || uri.equals("/GymLog/users/verify-user")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = getTokenRequisition(request);

            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                String json = "{\"error\": \"Token JWT ausente\"}";
                response.getWriter().write(json);
                response.getWriter().flush();
                return;
            }

            if (token != null) {
                String email = tokenService.verifyToken(token);
                UserEntity user = userRepository.findByEmailIgnoreCaseAndVerifiedTrue(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou não verificado"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (DataIntegrityViolationException ex) {
            SecurityContextHolder.clearContext();

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            String json = "{\"error\": \"Erro de integridade de dados: " + ex.getMessage() + "\"}";
            response.getWriter().write(json);
            response.getWriter().flush();

        } catch (RuntimeException ex) {
            SecurityContextHolder.clearContext();

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String json = "{\"error\": \"Erro de autenticação: " + ex.getMessage() + "\"}";
            response.getWriter().write(json);
            response.getWriter().flush();

        } catch (Exception ex) {
            SecurityContextHolder.clearContext();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.setContentType("application/json");
            String json = "{\"error\": \"Erro interno do servidor: " + ex.getMessage() + "\"}";
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
