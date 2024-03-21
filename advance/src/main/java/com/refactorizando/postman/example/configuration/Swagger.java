package com.refactorizando.postman.example.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Swagger {
    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder().group("WorkShop").pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearer-jwt",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)
                                                .name("Authorization")))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")))
                .info(
                        new Info()
                                .title("WorkShop Postman")
                                .description("WorkShop Postman")
                                .version("v0.0.1")
                                .license(new License().name("Decemberlabs").url("www.decemberlabs.com.uy")));
    }
}
