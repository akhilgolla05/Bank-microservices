package com.learnmicroservices.cards.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnmicroservices.cards.dto.CustomerDto;


@FeignClient(name = "ACCOUNTS")
@LoadBalancerClient(name = "ACCOUNTS")
public interface AccountFeignClient {
	
	@GetMapping("api/accounts/fetch")
	public CustomerDto fetchAccountDetails(@RequestParam String mobileNumber);
	
	

}
