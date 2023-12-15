package com.ab.saga.shipmentservice.infrastructure.axon;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.messaging.StreamableMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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

}
