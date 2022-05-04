package com.ateupeonding.paymentservice.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MessagingConfiguration {


    public static final String PAYMENT_REQUEST_QUEUE = "paymentRequest";
    public static final String PAYMENT_RESPONSE_QUEUE = "paymentRequest";

    @Bean
    public Queue paymentRequest() {
        return new Queue(PAYMENT_REQUEST_QUEUE, true);
    }


    @Bean
    public Queue paymentResponse() {
        return new Queue(PAYMENT_RESPONSE_QUEUE, true);
    }


    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
