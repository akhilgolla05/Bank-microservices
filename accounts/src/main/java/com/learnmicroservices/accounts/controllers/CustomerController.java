package com.learnmicroservices.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.accounts.dto.CustomerDetailsDto;
import com.learnmicroservices.accounts.services.ICustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/fetch")
	//@CircuitBreaker(name = "",fallbackMethod = "fallback method-name") // by using RestTemplate, we can handle this way
	public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber) {
		
		log.info("CustomerController :: fetchCustomerDetails");
		return customerService.fetchCustomerDetailsDto(mobileNumber);
	}
	
	
	/*
	 * 
	 * @GetMapping("/fetch")
	@CircuitBreaker(name = "a",fallbackMethod = "fallback method-name") // by using RestTemplate, we can handle this way
	public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber) {
		
		log.info("CustomerController :: fetchCustomerDetails");
		return customerService.fetchCustomerDetailsDto(mobileNumber);
	}
	
	application.yml
	
	resilience4j:
  circuitbreaker:
    configs: 
      a:
        sliding-window-size: 5
        permitted-number-of-calls-in-half-open-state: 2
        failure-rate-threshold: 50
        wait-duration-in-open-state: 30s
	 */
	
	
	

}
