package br.com.apirestfull.apigateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(
                new Info().title("RESTful API with Java 18 and SpringBoot 3")
                        .version("v1")
                        .description("Restful API for studying.")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0").url(""))
        );
    }
}
