package com.learnmicroservices.cards.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnmicroservices.cards.dto.LoanDto;


@FeignClient(name = "LOANS")
@LoadBalancerClient(name = "LOANS")
public interface LoansFeignClient {
	
	@GetMapping("api/loans/fetch")
	public LoanDto fetchLoan(@RequestParam String mobileNumber);

}
