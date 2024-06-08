package com.learnmicroservices.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnmicroservices.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	Optional<Loan> findByMobileNumber(String mobileNumber);
	
	Optional<Loan> findByloanNumber(String loanNumber);

}
