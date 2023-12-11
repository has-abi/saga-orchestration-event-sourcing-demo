package com.ab.saga.paymentservice.infrastructure.axon;

import com.thoughtworks.xstream.XStream;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.messaging.StreamableMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonSagaConfig {
    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerPooledStreamingEventProcessor(
                "AxonSagaProcessor",
                org.axonframework.config.Configuration::eventStore,
                (configuration, builder) -> builder.initialToken(StreamableMessageSource::createTailToken)
        );
    }

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[]{"com.ab.**"});
        return xStream;
    }
}
