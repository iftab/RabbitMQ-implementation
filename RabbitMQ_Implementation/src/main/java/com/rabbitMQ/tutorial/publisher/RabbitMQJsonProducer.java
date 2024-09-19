package com.rabbitMQ.tutorial.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitMQ.tutorial.dto.User;

@Service
public class RabbitMQJsonProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
   
	@Value("${rabbitmq.queue.exchange-name}")
	private String exhange_name;
	
	@Value("${rabbitmq.queue.json.queue-name}")
	private String queue_name;
	
	@Value("${rabbitmq.queue.json.routing_key}")
	private String routing_key;
	
	private RabbitTemplate rabbitTemplate;
	
	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendJsonMessage(User user) {
		LOGGER.info(String.format("Json sending message ->%s", user.toString()));
		rabbitTemplate.convertAndSend(exhange_name, routing_key, user);
	}
}
