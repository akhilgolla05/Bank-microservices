package com.learnmicroservices.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
	
	/*
	 * if u want to maintain the same tract-id across all the services for a request, then 
	use the dependency called feign-micrometer.
	
	and also we need add the bean object(MicrometerCapability), used for auto-config purpose
	
	only we need to add in Account service, since we are requesting AccountDetails from 
	Account service.
	 */
	
	@Bean
	public MicrometerCapability capability(final MeterRegistry meterRegistry) {
		return new MicrometerCapability(meterRegistry);
	}

}
