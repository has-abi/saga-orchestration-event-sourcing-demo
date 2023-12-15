package com.ab.saga.shipmentservice.infrastructure.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/api/ms-shipment/v1/", description = "Shipment Service")
        }
)
@Configuration
public class SwaggerConfig {
}
