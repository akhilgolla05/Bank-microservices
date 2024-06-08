package com.learnmicroservices.loans.entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	
	private String mobileNumber;
	private String loanNumber;
	private String loanType;
	
	@Enumerated(EnumType.STRING) // if we specify, this enum data will be stored as ENUM type(String)
	private LoanType lType;
	
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
	
	

}
