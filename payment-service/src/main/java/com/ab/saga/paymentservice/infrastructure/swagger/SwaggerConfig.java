package com.ab.saga.paymentservice.infrastructure.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/api/ms-payment/v1/", description = "Payment Service")
        }
)
@Configuration
public class SwaggerConfig {
}
