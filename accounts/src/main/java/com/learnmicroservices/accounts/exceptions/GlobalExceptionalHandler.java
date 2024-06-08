package com.learnmicroservices.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage CustomerAlreadyExistsExceptionHandler(CustomerAlreadyExistsException ex) {
		
		ErrorMessage error = new ErrorMessage();
		error.setMessage(ex.getMessage());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setTimeStamp(System.currentTimeMillis());
		
		return error;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> ResourceNotFoundException(ResourceNotFoundException ex) {
		
		ErrorMessage error = new ErrorMessage();
		error.setMessage(ex.getMessage());
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> ResourceNotFoundException(Exception ex) {
		
		ErrorMessage error = new ErrorMessage();
		error.setMessage("Some thing went wrong, please try again!");
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
