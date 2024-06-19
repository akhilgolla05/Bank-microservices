package com.learnmicroservices.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.learnmicroservices.accounts.dto.AccountContactInfo;

import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

//to enable config properties - at start-up all the properties will be loaded in below class
@EnableConfigurationProperties(AccountContactInfo.class)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
	
	/*
	 * if u want to maintain the same trace-id across all the services for a request, then 
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
