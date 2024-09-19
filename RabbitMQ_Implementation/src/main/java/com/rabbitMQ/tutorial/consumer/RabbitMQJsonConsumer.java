package com.rabbitMQ.tutorial.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitMQ.tutorial.dto.User;

@Service
public class RabbitMQJsonConsumer {
  
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	@RabbitListener(queues = {"${rabbitmq.queue.queue-name}"})
	public void consumeJson(User user) {
		LOGGER.info(String.format("Consume JSON message -> %s", user.toString()));
	}
	
}
