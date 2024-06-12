package com.learnmicroservices.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.accounts.dto.CustomerDetailsDto;
import com.learnmicroservices.accounts.services.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/fetch")
	public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber) {
		
		log.info("CustomerController :: fetchCustomerDetails");
		return customerService.fetchCustomerDetailsDto(mobileNumber);
	}
	
	
	

}
