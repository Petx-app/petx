package com.petx.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        List<Tag> tags = Arrays.asList(
                new Tag().name("Public API").description("Endpoints públicos"),
                new Tag().name("Private API").description("Endpoints privados")
        );

        return new OpenAPI()
                .info(new Info()
                        .title("PETX - API Documentation")
                        .version("1.0")
                        .description("API documentation for the application"))
                .tags(tags);
    }
    }
