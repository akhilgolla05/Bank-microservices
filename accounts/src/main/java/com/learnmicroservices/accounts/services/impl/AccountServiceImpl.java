package com.learnmicroservices.accounts.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.learnmicroservices.accounts.dto.AccountDto;
import com.learnmicroservices.accounts.dto.CustomerDto;
import com.learnmicroservices.accounts.entity.Account;
import com.learnmicroservices.accounts.entity.Customer;
import com.learnmicroservices.accounts.exceptions.CustomerAlreadyExistsException;
import com.learnmicroservices.accounts.exceptions.ResourceNotFoundException;
import com.learnmicroservices.accounts.repository.AccountRepository;
import com.learnmicroservices.accounts.repository.CustomerRepository;
import com.learnmicroservices.accounts.services.IAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements IAccountService{

	private AccountRepository accountRepository;
	
	private CustomerRepository customerRepository;
	
	
	@Override
	public void createAccount(CustomerDto customerDto) {
		
		log.info("AccountServiceImpl :: createAccount");
		
		Optional<Customer> customerAccount = 
				customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		
		if(customerAccount.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer Already Exists with the given mobile number");
			
		}
		
		//convert customerDto to customer-entity
			//ObjectMapper - used to convert Json
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		
		Customer dbCustomer = customerRepository.save(customer);
		
		log.info("AccountServiceImpl :: createAccount - customer successfully created");
		
		//create account
		accountRepository.save(createAccount(dbCustomer));
		
		log.info("AccountServiceImpl :: createAccount - Account saved to DB");
		
	}

	private Account createAccount(Customer dbCustomer) {
		
		
		Account account = new Account();
		account.setCustomerId(dbCustomer.getCustomerId());
		account.setAccountType("SAVINGS");
		account.setBranchAddress("Hyderabad");
		
		Long accNumber = 1000000000L + new Random().nextInt(400000);
		account.setAccountNumber(accNumber);
		
		return account;
		
		
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		
		log.info("AccountServiceImpl :: fetchAccount");
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(()-> new ResourceNotFoundException("Customer Not Found with Mobile Number : "+mobileNumber));
		
		Account account = accountRepository.findByCustomerId(customer.getCustomerId())
			.orElseThrow(()-> new ResourceNotFoundException("Account Not Found with Mobile Number : "+ mobileNumber));
				
		log.info("AccountServiceImpl :: fetchAccount - successfully fetched customer and account information");
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(account, accountDto);
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customer, customerDto);
		customerDto.setAccountDto(accountDto);
		log.info("AccountServiceImpl :: fetchAccount - set the values for CustomerDto");
		return customerDto;
		
		
	}

	@Override
	public boolean updateAccouunt(CustomerDto customerDto) {
		log.info("AccountServiceImpl :: updateAccouunt");
		boolean isUpdated = false;
		
		AccountDto accountDto = customerDto.getAccountDto();
		
		if(accountDto != null) {
			
			Account account = accountRepository.findById(accountDto.getAccountNumber())
					.orElseThrow(()-> new ResourceNotFoundException("Account Not Found!"));
			
			
			account.setBranchAddress(accountDto.getBranchAddress());
			
			Customer customer = customerRepository.findById(account.getCustomerId())
				.orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
			
			customer.setEmail(customerDto.getEmail());
			customer.setMobileNumber(customerDto.getMobileNumber());
			customer.setName(customerDto.getName());
			
			customerRepository.save(customer);
			accountRepository.save(account);
			log.info("AccountServiceImpl :: updateAccouunt - updated successfully");
			isUpdated = true;
		}else {
			throw new RuntimeException("Update Operation Failed...");
			
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		log.info("AccountServiceImpl :: deleteAccount");
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(()-> new ResourceNotFoundException("Customer Not Found!"));
		
		//hard delete - in real time u should not do this
			// u have to do soft-delete - make User inactive(updating status value)
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());;
		log.info("AccountServiceImpl :: deleteAccount - deleted successfully");
		return true;
	}

}
