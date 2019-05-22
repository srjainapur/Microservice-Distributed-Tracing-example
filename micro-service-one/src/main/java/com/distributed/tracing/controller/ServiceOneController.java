package com.distributed.tracing.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ServiceOneController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceOneController.class.getName());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient discoverClient;
	
	@RequestMapping(value="/serviceOne/{message}", method=RequestMethod.GET)
	public String callServiceTwo(@PathVariable("message") String message) {
		logger.info("BEGIN :: callServiceTwo with param message " + message);
		
		String url = getServiceUrl();
		logger.info("callServiceTwo URL " + url);
		
		// HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		
		ResponseEntity<String> exchange = restTemplate.exchange(url +"callServiceTwo/"+message, HttpMethod.GET, entity, String.class);
		logger.info("END :: callServiceTwo with param body " + exchange.getBody());
		return exchange.getBody();
	}
	
	public String getServiceUrl() {
		InstanceInfo nextServerFromEureka = discoverClient.getNextServerFromEureka("micro-service-two", false);
		return nextServerFromEureka.getHomePageUrl();
		//discoveryClient.getNextServerFromEureka("STORES", false);
	}
}
