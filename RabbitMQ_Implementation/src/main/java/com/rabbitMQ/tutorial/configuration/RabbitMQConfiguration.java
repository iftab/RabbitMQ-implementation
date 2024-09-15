package com.rabbitMQ.tutorial.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.queue.exchange-name}")
	private String exchange_name;
	
	@Value("${rabbitmq.queue.routing_key}")
	private String routingKey;
	
	// Spring Bean for rabbitMQ Queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	// Creating exchange bean
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange_name);
	}
	
	// Binding between queue and exchange with routingKey
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(routingKey);
	}
	
	// Spring Auto-configuration will automatically create bean below infrastructure beans.
	// ConnectionFactoy
	// RabbitTemplate
	//RabbitAdmin
}