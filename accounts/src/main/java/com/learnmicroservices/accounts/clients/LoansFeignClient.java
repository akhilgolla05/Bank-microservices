package com.learnmicroservices.accounts.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnmicroservices.accounts.dto.LoanDto;


//implementing fall-back - whenever Loans Application is down, implements LoanFallBack 
@FeignClient(name = "LOANS", fallback = LoansFallBack.class) // it routes to cards application through eureka-server

/*
 * cards may run on multiple port-numbers, to handle the request - internally uses ROUND-ROBIN algo
 * 
 * u will get IP-ADDRESS and PORT number
 */
@LoadBalancerClient(name = "LOANS" )
public interface LoansFeignClient {

	@GetMapping("api/loans/fetch")
	public LoanDto fetchLoan(@RequestParam String mobileNumber);
}
