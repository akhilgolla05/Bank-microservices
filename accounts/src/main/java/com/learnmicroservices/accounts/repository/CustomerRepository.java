package com.learnmicroservices.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnmicroservices.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByMobileNumber(String mobileNumber);

}
