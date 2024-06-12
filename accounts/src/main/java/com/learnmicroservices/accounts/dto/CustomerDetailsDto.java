package com.learnmicroservices.accounts.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

// response
@Data
public class CustomerDetailsDto {
	
	
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String mobileNumber;
	
	private AccountDto accountDto;
	
	private CardDto cardDto;
	
	private LoanDto loanDto;
	
	


}
