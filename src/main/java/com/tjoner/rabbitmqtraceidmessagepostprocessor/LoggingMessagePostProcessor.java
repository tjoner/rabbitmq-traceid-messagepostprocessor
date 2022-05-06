package com.tjoner.rabbitmqtraceidmessagepostprocessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

@Slf4j
@RequiredArgsConstructor
public class LoggingMessagePostProcessor implements MessagePostProcessor {

    private final String source;

    @Override
    public Message postProcessMessage(Message message) {

        log.info("MessagePostProcessor - {}", source);
        return message;
    }
}
