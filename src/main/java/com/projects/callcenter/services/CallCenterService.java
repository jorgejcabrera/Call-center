package com.projects.callcenter.services;

import com.projects.callcenter.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallCenterService {

    private static final String MESSAGE = "Hello world!";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void receiveCall() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, MESSAGE);
    }
}
