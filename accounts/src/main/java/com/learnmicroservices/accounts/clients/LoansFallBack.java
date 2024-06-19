package com.learnmicroservices.accounts.clients;

import org.springframework.stereotype.Component;

import com.learnmicroservices.accounts.dto.LoanDto;

@Component
public class LoansFallBack implements LoansFeignClient{

	@Override
	public LoanDto fetchLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
