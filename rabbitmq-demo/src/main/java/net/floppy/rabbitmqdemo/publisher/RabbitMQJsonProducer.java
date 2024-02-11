package net.floppy.rabbitmqdemo.publisher;

import net.floppy.rabbitmqdemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.name.exchange}")
    private String exchange;
    @Value("${rabbitmq.name.jsonRouting}")
    private String jsonRouting_key;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user)
    {
        LOGGER.info(String.format("Json message sent -->  %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonRouting_key,user);
    }

}
