package com.learnmicroservices.cards.dto;

import lombok.Data;

@Data
public class CardHolderDetailsDto {

	private String mobileNumber;
	private String cardNumber;     
	private String cardType;
	private int totalLimit;
	private int amountUsed;
	private int availableAmount;
	
	private CustomerDto customerDto;
	
	private LoanDto loanDto;
	
}
