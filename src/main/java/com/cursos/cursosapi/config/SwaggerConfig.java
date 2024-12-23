package com.cursos.cursosapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Gestão de Cursos").description("API Responsável pela gestão de cursos")
                        .version("1"))
                .schemaRequirement("jwt_auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme(){
        return new SecurityScheme().name("jwt-auth").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");
    }
}