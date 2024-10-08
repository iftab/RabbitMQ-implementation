package com.rabbitMQ.tutorial.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.queue.json.queue-name}")
	private String jsonqueue;
	
	@Value("${rabbitmq.queue.exchange-name}")
	private String exchange_name;
	
	@Value("${rabbitmq.queue.routing_key}")
	private String routingKey;
	
	@Value("${rabbitmq.queue.json.routing_key}")
	private String jsonroutingKey;
	
	// Spring Bean for rabbitMQ Queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonqueue);
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
	
	@Bean
	public Binding jsonbinding() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange())
				.with(jsonroutingKey);
	}
	
	@Bean
	public MessageConverter coverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
	   RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	   rabbitTemplate.setMessageConverter(coverter());
	   return rabbitTemplate;
	}
	
	// Spring Auto-configuration will automatically create bean below infrastructure beans.
	// ConnectionFactoy
	// RabbitTemplate
	//RabbitAdmin
}