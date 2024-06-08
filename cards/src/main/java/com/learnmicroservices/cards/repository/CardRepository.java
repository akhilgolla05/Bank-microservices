package com.learnmicroservices.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnmicroservices.cards.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

	Optional<Card> findByMobileNumber(String mobileNumber);
	
	Optional<Card> findByCardNumber(String cardNumber);
}
