package com.learnmicroservices.cards.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//input request and output response 
@Data
public class CustomerDto {
	
	@NotNull
	@Size(min=4, max=10, message = "Please provide character length between 4 and 10")
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String mobileNumber;
	
	


}
