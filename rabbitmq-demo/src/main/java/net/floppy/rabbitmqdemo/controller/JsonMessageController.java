package net.floppy.rabbitmqdemo.controller;

import net.floppy.rabbitmqdemo.dto.User;
import net.floppy.rabbitmqdemo.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class JsonMessageController {
    private RabbitMQJsonProducer jsonProducer;

    public JsonMessageController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("producer")
    public ResponseEntity<String> sendMessageJson(@RequestBody User user)
    {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitMQ Broker..");
    }
}
