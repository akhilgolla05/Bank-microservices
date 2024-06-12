package com.learnmicroservices.cards.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.learnmicroservices.cards.clients.AccountFeignClient;
import com.learnmicroservices.cards.clients.LoansFeignClient;
import com.learnmicroservices.cards.dto.CardDto;
import com.learnmicroservices.cards.dto.CardHolderDetailsDto;
import com.learnmicroservices.cards.dto.CustomerDto;
import com.learnmicroservices.cards.dto.LoanDto;
import com.learnmicroservices.cards.entity.Card;
import com.learnmicroservices.cards.exceptions.CardAlreadyExistsException;
import com.learnmicroservices.cards.exceptions.ResourceNotFoundException;
import com.learnmicroservices.cards.repository.CardRepository;
import com.learnmicroservices.cards.services.ICardService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor // allow dependency Injection
@Slf4j
public class CardServiceImpl implements ICardService{
	
	private CardRepository cardRepository;
	
	private AccountFeignClient accountFeignClient;
	
	private LoansFeignClient loansFeignClient;

	@Override
	public void createCard(String mobileNumber) {
		log.info("CardServiceImpl :: createCard");
		Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
		
		if(optionalCard.isPresent()) {
				throw new CardAlreadyExistsException("Card Already Exists with given Mobile Number : "+mobileNumber);	
		}
		createNewCard(mobileNumber);
		log.info("CardServiceImpl :: createCard - card created successfully");
		
	}
	
	private void createNewCard(String mobileNumber) {
		
		Card card = new Card();
		
		long randomNumber = 100000000L + new Random().nextInt(90000);
		card.setCardNumber(String.valueOf(randomNumber));
		card.setMobileNumber(mobileNumber);
		card.setCardType("CREDIT_CARD");
		card.setAvailableAmount(1000000);
		card.setAmountUsed(0);
		card.setTotalLimit(1000000);
		
		cardRepository.save(card);
		
	}

	@Override
	public CardDto fetchCard(String mobileNumber) {
		
		log.info("CardServiceImpl :: fetchCard");
		Card dbCard = cardRepository.findByMobileNumber(mobileNumber)
		.orElseThrow(()->new ResourceNotFoundException("Card Not Found with given Mobile Number : "+ mobileNumber));
		
		log.info("CardServiceImpl :: Card Available");
		CardDto cardDto = new CardDto();
		BeanUtils.copyProperties(dbCard, cardDto);
		
		return cardDto;
		
	}

	@Override
	public boolean updateCard(CardDto cardDto) {
		
		log.info("CardServiceImpl :: updateCard");
		Card dbCard = cardRepository.findByCardNumber(cardDto.getCardNumber())
		.orElseThrow(()-> new ResourceNotFoundException("Card Does Not Exists : " + cardDto.getCardNumber()));
		
		dbCard.setAmountUsed(cardDto.getAmountUsed());
		dbCard.setMobileNumber(cardDto.getMobileNumber());
		dbCard.setAvailableAmount(cardDto.getAvailableAmount());
		
		cardRepository.save(dbCard);
		
		log.info("CardServiceImpl :: updateCard - updated successfully");
		return true;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		
		log.info("CardServiceImpl :: deleteCard");
		Card dbCard = cardRepository.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Card Not Found with given Mobile Number : "+mobileNumber));
		
		cardRepository.deleteById(dbCard.getCardId());
		
		log.info("CardServiceImpl :: deleteCard - deleted successfully");
		return true;
	}

	
	@Override
	public CardHolderDetailsDto getCardHolderDetails(String mobileNumber) {
		
		log.info("CardServiceImpl :: getCardHolderDetails");
		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(()-> new ResourceNotFoundException("Card Not Found with given Mobile Number : "+ mobileNumber));
		
		
		LoanDto laonDto =  loansFeignClient.fetchLoan(mobileNumber);
		
		CustomerDto customerDto =  accountFeignClient.fetchAccountDetails(mobileNumber);
		
		CardHolderDetailsDto cardHolderDetailsDto = new CardHolderDetailsDto();
		BeanUtils.copyProperties(card, cardHolderDetailsDto);
		
		CustomerDto theCustomerDto = new CustomerDto();
		theCustomerDto.setEmail(customerDto.getEmail());
		theCustomerDto.setMobileNumber(mobileNumber);
		theCustomerDto.setName(customerDto.getName());
		
		cardHolderDetailsDto.setCustomerDto(theCustomerDto);
		
		cardHolderDetailsDto.setLoanDto(laonDto);
		
		
		return cardHolderDetailsDto;
	}
	
	

}
