package net.floppy.rabbitmqdemo.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.jsonQueue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.name.exchange}")
    private String exchange;
    @Value("${rabbitmq.name.routing}")
    private String routing_key;

    @Value("${rabbitmq.name.jsonRouting}")
    private String jsonRouting_key;

    //Creating Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //Creating Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //binding queue to exchange with routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routing_key);
    }


/*  //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
    RabbitMQ Auto configure all these three.
    every package choose from core
    */

    //JSON communication
    @Bean
    public Queue jsonQueue()
    {
        return new Queue(jsonQueue);
    }

    @Bean
    public Binding jsonBinding()
    {
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRouting_key);
    }

    //JSON message convert
    @Bean
    public MessageConverter converter()
    {
        return new Jackson2JsonMessageConverter();
    }

    //Connection-factory package from springframework.amqp
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
