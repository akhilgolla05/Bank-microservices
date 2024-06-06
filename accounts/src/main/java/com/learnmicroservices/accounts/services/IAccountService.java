package com.learnmicroservices.accounts.services;

import com.learnmicroservices.accounts.dto.CustomerDto;

public interface IAccountService {
	
	void createAccount(CustomerDto customerDto);
	
	CustomerDto fetchAccount(String mobileNumber);
	
	boolean updateAccouunt(CustomerDto customerDto);
	
	boolean deleteAccount(String mobileNumber);

}
