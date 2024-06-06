package com.learnmicroservices.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnmicroservices.cards.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
