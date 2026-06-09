package br.com.fiap.orbifreight.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API OrbiFreight")
                        .version("1.0.0")
                        .description("API RESTful desenvolvida para a Global Solution - FIAP. Implementação de HATEOAS e tratamento global de exceções.")
                        .contact(new Contact().name("Maria Gabriela Landim Severo").email("seu-email@fiap.com.br"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}