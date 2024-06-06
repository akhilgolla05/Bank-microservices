package com.learnmicroservices.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnmicroservices.accounts.entity.Account;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	
	Optional<Account> findByCustomerId(Long customerId);
	
	@Transactional  //DML operations
	void deleteByCustomerId(Long customerId); 
	

}
