package com.example.demo.service.rabbitmq;

import com.example.demo.entity.Event;
import com.example.demo.repo.RabbitMqEventRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class QueueListener {
@Autowired
    RabbitMqEventRepo rabbitMqEventRepo;
    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String message) {
        Event event=new Event();
        event.setEvent(message);
        rabbitMqEventRepo.save(event);
        System.out.println("Message " + event);

    }

}
