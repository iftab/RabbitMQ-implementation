package com.rabbitMQ.tutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitMQ.tutorial.dto.User;
import com.rabbitMQ.tutorial.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
 
	private RabbitMQJsonProducer  rabbitMQJsonProducer;
	
	public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
		this.rabbitMQJsonProducer = rabbitMQJsonProducer;
	}
	
	@PostMapping("/publishJson")
	public ResponseEntity<Object> sendJsonMessage(@RequestBody User user){
		rabbitMQJsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok("Message send to rabbitMQ Queue");
	}
}
