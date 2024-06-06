package com.learnmicroservices.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnmicroservices.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
