package com.tjoner.rabbitmqtraceidmessagepostprocessor.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoProducer {
    public static final String MESSAGE_PAYLOAD = """
            {
              "test": "abc"
            }""";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @NewSpan
    public void publish() {
        log.info("Sending payload...");
        rabbitTemplate.convertAndSend("demo.exchange", "demo.routingkey", MESSAGE_PAYLOAD);
    }
}
