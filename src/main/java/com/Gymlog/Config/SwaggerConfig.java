package com.Gymlog.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi() {

        // Informações da API
        Contact contact = new Contact()
                .name("Patrick")
                .email("patrick.almeida06@gmail.com");

        Info info = new Info()
                .title("GymLog API")
                .version("v1")
                .description("Projeto para GymLog")
                .contact(contact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Insira o token JWT no formato: Bearer {token}");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement) // Aplica a segurança globalmente
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme));
    }
}
