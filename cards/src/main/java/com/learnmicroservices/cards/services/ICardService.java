package com.learnmicroservices.cards.services;

import com.learnmicroservices.cards.dto.CardDto;
import com.learnmicroservices.cards.entity.Card;

public interface ICardService {
	
	
	void createCard(String mobileNumber);
	
	CardDto fetchCard(String mobileNumber);
	
	boolean updateCard(CardDto cardDto);
	
	boolean deleteCard(String mobileNumber);

}
