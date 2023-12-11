package com.ab.saga.orderservice.infrastructure.messaging;

import com.rabbitmq.client.Channel;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "amqp")
public class AmqpConfig {
    private List<QueueBinding> bindings;

    @Bean
    public Declarables queues() {
        List<Queue> queues = bindings.stream()
                .map(binding -> new Queue(binding.getQueue()))
                .toList();
        return new Declarables(queues.toArray(new Queue[0]));
    }

    @Bean
    public Declarables exchanges() {
        return new Declarables(bindings.stream()
                .map(binding -> new DirectExchange(binding.getExchange())).toArray(Exchange[]::new));
    }

    @Bean
    public Declarables bindings() {
        List<Binding> bindingList = this.bindings.stream()
                .map(binding -> new Binding(binding.getQueue(), Binding.DestinationType.QUEUE, binding.getExchange(), binding.getRoutingKey(), null))
                .toList();
        return new Declarables(bindingList.toArray(new Binding[0]));
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public SpringAMQPMessageSource myQueueMessageSource(AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {

            @RabbitListener(queues = "${amqp.queues.order_saga}")
            @Override
            public void onMessage(Message message, Channel channel) {
                super.onMessage(message, channel);
            }
        };
    }
}
