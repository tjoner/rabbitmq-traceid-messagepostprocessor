package com.tjoner.rabbitmqtraceidmessagepostprocessor.config;

import com.tjoner.rabbitmqtraceidmessagepostprocessor.LoggingMessagePostProcessor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("demo.exchange");
    }

    @Bean
    public Queue ksConsentStatusQueue() {
        return QueueBuilder
                .durable("demo.queue")
                .build();
    }

    @Bean
    public Binding ksConsentStatusBinding() {
        return BindingBuilder.bind(ksConsentStatusQueue())
                .to(exchange())
                .with("demo.routingkey");
    }

    @Bean
    public RabbitTemplate protectedRabbitTemplate(RabbitTemplateConfigurer configurer,
                                                  ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        configurer.configure(template, connectionFactory);

        // set logging message post processor when producing messages
        template.setBeforePublishPostProcessors(new LoggingMessagePostProcessor("Producer"));

        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory loggingSimpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                                            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        var factory = new SimpleRabbitListenerContainerFactory();

        // use the configurer to apply boot properties.
        configurer.configure(factory, connectionFactory);

        // set logging message post processor when consuming messages
        factory.setAfterReceivePostProcessors(new LoggingMessagePostProcessor("Consumer"));

        return factory;
    }
}
