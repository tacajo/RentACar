package com.authService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {
    @Value("${fanout.exchange}")
    private String fanoutExchange;
    @Value("${queue.name}")
    private String queueName;
    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(fanoutExchange);
    }
    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config) throws Exception {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://yzwtnzwe:xQpcN_6nuCDzm07c8PiBLHfMtvUsbM1n@buck.rmq.cloudamqp.com/yzwtnzwe");

        return connectionFactory;

    }
}
