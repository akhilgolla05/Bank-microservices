package com.learnmicroservices.accounts.services;

import com.learnmicroservices.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

	CustomerDetailsDto fetchCustomerDetailsDto(String mobileNumber);
	
	
}
