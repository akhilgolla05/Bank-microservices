package com.learnmicroservices.accounts.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {
	
	private String message;
	private int statusCode;
	private String status;
	private Long timeStamp;
	
	

}
