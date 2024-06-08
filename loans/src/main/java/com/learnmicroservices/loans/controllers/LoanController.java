package com.learnmicroservices.loans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.loans.dto.LoanDto;
import com.learnmicroservices.loans.services.ILoanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/loans")
@Slf4j
public class LoanController {
	
	@Autowired
	private ILoanService loanService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	private String createLoan(@RequestParam String mobileNumber) {
		
		log.info("LoanController :: createLoan");
		loanService.createLoan(mobileNumber);
		return "Loan Created Successfully";
	}
	
	@GetMapping("/fetch")
	private LoanDto fetchLoan(@RequestParam String mobileNumber) {
		
		log.info("LoanController :: fetchLoan");
		return loanService.fetchLoan(mobileNumber);
	}
	
	@PutMapping("/update")
	private boolean updateLoan(@RequestBody LoanDto loanDto) {
		
		log.info("LoanController :: updateLoan");
		return loanService.updateLoan(loanDto);
	
	}
	
	@DeleteMapping("/delete")
	private boolean deleteLoan(@RequestParam String mobileNumber) {
		
		log.info("LoanController :: deleteLoan");
		return loanService.deleteLoan(mobileNumber);
		
	}
	
	
	

}
