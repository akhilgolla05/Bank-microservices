package com.learnmicroservices.accounts.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learnmicroservices.accounts.dto.CardDto;

@Component
public class CardsFallBack implements CardsFeignClent{

	@Override
	public ResponseEntity<CardDto> fetchCardDetails(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
