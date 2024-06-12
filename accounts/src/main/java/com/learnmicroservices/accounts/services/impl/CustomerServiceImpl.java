package com.learnmicroservices.accounts.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learnmicroservices.accounts.clients.CardsFeignClent;
import com.learnmicroservices.accounts.clients.LoansFeignClient;
import com.learnmicroservices.accounts.dto.AccountDto;
import com.learnmicroservices.accounts.dto.CardDto;
import com.learnmicroservices.accounts.dto.CustomerDetailsDto;
import com.learnmicroservices.accounts.dto.LoanDto;
import com.learnmicroservices.accounts.entity.Account;
import com.learnmicroservices.accounts.entity.Customer;
import com.learnmicroservices.accounts.exceptions.ResourceNotFoundException;
import com.learnmicroservices.accounts.repository.AccountRepository;
import com.learnmicroservices.accounts.repository.CustomerRepository;
import com.learnmicroservices.accounts.services.ICustomerService;


@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoansFeignClient loansFeignClient;
	
	@Autowired
	private CardsFeignClent cardsFeignClent;
	

	@Override
	public CustomerDetailsDto fetchCustomerDetailsDto(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Customer Not Found with mobile Number : " + mobileNumber));
		
		
		Account account = accountRepository.findByCustomerId(customer.getCustomerId())
		.orElseThrow(()-> new ResourceNotFoundException("Account Not Found with CustomerId : " + customer.getCustomerId()));
		
		LoanDto loanDto = loansFeignClient.fetchLoan(mobileNumber);
		
		ResponseEntity<CardDto> cardDto = cardsFeignClent.fetchCardDetails(mobileNumber);
		
		
		
		CustomerDetailsDto customerDetailsDto = preparingCustomerDetailsDto(customer, account, loanDto, cardDto);
		
	
		return customerDetailsDto;
	}


	private CustomerDetailsDto preparingCustomerDetailsDto(Customer customer, Account account, LoanDto loanDto,
			ResponseEntity<CardDto> cardDto) {
		CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
		BeanUtils.copyProperties(customer, customerDetailsDto);
		
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(account, accountDto);
		
		customerDetailsDto.setAccountDto(accountDto);
		
//		if(cardDto != null)
//			customerDetailsDto.setCardDto(cardDto);
		
		if(cardDto.getStatusCode().is2xxSuccessful())
			customerDetailsDto.setCardDto(cardDto.getBody());
		
		if(loanDto != null)
			customerDetailsDto.setLoanDto(loanDto);
		
		return customerDetailsDto;
	}

}
