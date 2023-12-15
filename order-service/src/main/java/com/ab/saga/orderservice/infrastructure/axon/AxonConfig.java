package com.ab.saga.orderservice.infrastructure.axon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerPooledStreamingEventProcessor(
                "AxonSagaProcessor",
                org.axonframework.config.Configuration::eventStore,
                (configuration, builder) -> builder.initialToken(StreamableMessageSource::createTailToken)
        );
    }

    @Bean
    @Primary
    public Serializer axonJacksonSerializer(ObjectMapper objectMapper) {
        return JacksonSerializer.builder()
                .objectMapper(objectMapper)
                .defaultTyping()
                .build();
    }

}
