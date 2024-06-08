package com.learnmicroservices.loans.exceptions;

public class LoanAlreadyExistsException extends RuntimeException {

	public LoanAlreadyExistsException(String message) {
		super(message);
	}
}
