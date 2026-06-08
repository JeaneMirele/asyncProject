package com.project.async.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_RELATORIOS = "relatorios.queue";
    public static final String EXCHANGE_RELATORIOS = "relatorios.exchange";
    public static final String ROUTING_RELATORIOS = "relatorios.routing";

    @Bean
    public Queue queueRelatorio() {
        return new Queue(QUEUE_RELATORIOS, true);
    }

    @Bean
    public DirectExchange exchangeRelatorio() {
        return new DirectExchange(EXCHANGE_RELATORIOS);
    }

    @Bean
    public Binding bindingRelatorio(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_RELATORIOS);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
