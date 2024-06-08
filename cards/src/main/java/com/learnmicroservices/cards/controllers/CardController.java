package com.learnmicroservices.cards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.cards.dto.CardDto;
import com.learnmicroservices.cards.services.ICardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/cards")
@Slf4j
public class CardController {
	
	@Autowired
	private ICardService iCardService;
	
	@PostMapping("/create")
	public String createCard(@RequestParam String mobileNumber) {
		log.info("CardController :: createCard");
		iCardService.createCard(mobileNumber);
		return "Card Created Successfully";
		
	}
	
	@GetMapping("/fetch")
	public CardDto fetchCardDetails(@RequestParam String mobileNumber) {
		log.info("CardController :: fetchCardDetails");
		return iCardService.fetchCard(mobileNumber);
		
	}
	
	@PutMapping("/update")
	public boolean updateCardDetails(@RequestBody CardDto cardDto) {
		log.info("CardController :: updateCardDetails");
		 return iCardService.updateCard(cardDto);
		
	}
	
	@DeleteMapping("/delete")
	public boolean deleteCardDetails(@RequestParam String mobileNumber) {
		log.info("CardController :: deleteCardDetails");
		 return iCardService.deleteCard(mobileNumber);
		
	}

}
