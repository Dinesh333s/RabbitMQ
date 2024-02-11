package net.floppy.rabbitmqdemo.controller;

import lombok.Getter;
import net.floppy.rabbitmqdemo.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class MessageController {
    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    //http:localhost:8080/publish?message=hello world
    @GetMapping("publish")
    public ResponseEntity<String> message(@RequestParam("message") String message)
    {
        rabbitMQProducer.sendQueue(message);
        return ResponseEntity.ok("message sent");
    }
}
