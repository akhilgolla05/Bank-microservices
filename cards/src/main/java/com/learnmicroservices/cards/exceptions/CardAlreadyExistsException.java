package com.learnmicroservices.cards.exceptions;

public class CardAlreadyExistsException extends RuntimeException{
	
	public CardAlreadyExistsException(String message) {
		super(message);
	}
	
	

}
