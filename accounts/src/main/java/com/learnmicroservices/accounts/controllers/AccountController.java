package com.learnmicroservices.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.accounts.dto.CustomerDto;
import com.learnmicroservices.accounts.services.IAccountService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/accounts")
@Slf4j
public class AccountController {
	
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createAccount(@RequestBody @Valid CustomerDto customerDto) {
		log.info("AccountController :: createAccount");
		accountService.createAccount(customerDto);
		return "Account Created Successfully";
	}
	
	@GetMapping("/fetch")
	public CustomerDto fetchAccountDetails(@RequestParam String mobileNumber) {
		log.info("AccountController :: fetchAccountDetails");
		return accountService.fetchAccount(mobileNumber);
	}
	
	@PutMapping("/update")
	public boolean updateAccountDetails(@RequestBody CustomerDto customerDto) {
		log.info("AccountController :: updateAccountDetails");
		return accountService.updateAccouunt(customerDto);
	}
	
	@DeleteMapping("/delete")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public boolean deleteAccountDetails(@RequestParam String mobileNumber) {
		log.info("AccountController :: updateAccountDetails");
		return accountService.deleteAccount(mobileNumber);
	}
	

}
