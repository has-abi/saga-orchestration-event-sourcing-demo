package com.ab.saga.orderservice.infrastructure.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/api/ms-order/v1/", description = "Order Service")
        }
)
@Configuration
public class SwaggerConfig {
}
