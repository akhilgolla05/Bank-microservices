package com.learnmicroservices.accounts.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnmicroservices.accounts.dto.CardDto;


@FeignClient(name = "CARDS") // it routes to cards application through eureka-server

/*
 * cards may run on multiple port-numbers, to handle the request - internally uses ROUND-ROBIN algo
 * 
 * u will get IP-ADDRESS and PORT number
 */
@LoadBalancerClient(name = "CARDS" )
public interface CardsFeignClent {
	
	
	@GetMapping("api/cards/fetch")
	public ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);

}
