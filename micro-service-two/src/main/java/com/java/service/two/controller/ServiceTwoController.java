package com.java.service.two.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceTwoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceTwoController.class.getName());
	
	@RequestMapping(value="/callServiceTwo/{message}", method=RequestMethod.GET)
	public String callServiceThree(@PathVariable("message") String message) {
		logger.info("BEGIN :: callServiceThree() message " + message);
		return "Hi " + message + " is returned from Microservice two";
	}
}
