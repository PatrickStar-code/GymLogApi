package com.Gymlog.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.dao.DataIntegrityViolationException;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FilterTokenJWT extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // para serializar JSON

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals("/login") || uri.equals("/refresh-token")
                    || uri.equals("/GymLog/users/register")
                    || uri.equals("/GymLog/users/verify-user")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = getTokenRequisition(request);

            if (token == null) {
                writeJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                        Map.of("error", "Token JWT ausente"));
                return;
            }

            String email = tokenService.verifyToken(token);
            UserEntity user = userRepository.findByEmailIgnoreCaseAndVerifiedTrue(email)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou não verificado"));

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (DataIntegrityViolationException ex) {
            SecurityContextHolder.clearContext();
            writeJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST,
                    Map.of("error", "Erro de integridade de dados", "detalhe", ex.getMessage()));

        } catch (RuntimeException ex) {
            SecurityContextHolder.clearContext();
            writeJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                    Map.of("error", "Erro de autenticação", "detalhe", ex.getMessage()));

        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            writeJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Map.of("error", "Erro interno do servidor", "detalhe", ex.getMessage()));
        }
    }

    private String getTokenRequisition(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private void writeJsonResponse(HttpServletResponse response, int status, Map<String, String> body) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), body);
    }
}
