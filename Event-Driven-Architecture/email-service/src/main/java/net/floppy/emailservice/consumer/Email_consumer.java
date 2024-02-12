package net.floppy.emailservice.consumer;

import net.floppy.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Email_consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Email_consumer.class);

    @RabbitListener(queues = "${rabbit.equeue.name}")
    public void email_consumer(OrderEvent orderEvent) {
        LOGGER.info(String.format("Email order received --> %s", orderEvent.toString()));

        //Email send to customer
    }
}
