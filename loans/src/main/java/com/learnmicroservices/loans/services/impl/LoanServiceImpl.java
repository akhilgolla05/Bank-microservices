package com.learnmicroservices.loans.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.learnmicroservices.loans.dto.LoanDto;
import com.learnmicroservices.loans.entity.Loan;
import com.learnmicroservices.loans.entity.LoanType;
import com.learnmicroservices.loans.exceptions.LoanAlreadyExistsException;
import com.learnmicroservices.loans.exceptions.ResourceNotFoundException;
import com.learnmicroservices.loans.repository.LoanRepository;
import com.learnmicroservices.loans.services.ILoanService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LoanServiceImpl implements ILoanService{
	
	private LoanRepository loanRepository;

	@Override
	public void createLoan(String mobileNumber) {
		log.info("LoanServiceImpl :: createLoan");
		Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
		
		if(optionalLoan.isPresent()) {
			throw new LoanAlreadyExistsException("Loan Already exixts");
		}
		
		Loan loan = new Loan();
		long randomNumber = 10000000L + new Random().nextInt(789699);
		loan.setLoanNumber(String.valueOf(randomNumber));
		loan.setLoanType("PERSONAL_LOAN");
		loan.setLType(LoanType.PERSONAL_LOAN); //using enum
		loan.setMobileNumber(mobileNumber);
		loan.setTotalLoan(5000);
		loan.setAmountPaid(0);
		loan.setOutstandingAmount(3000);
		
		
		loanRepository.save(loan);
		log.info("LoanServiceImpl :: createLoan - loan successfully created");
	}

	@Override
	public LoanDto fetchLoan(String mobileNumber) {
		log.info("LoanServiceImpl :: fetchLoan");
		Loan loan = loanRepository.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Loan Does not Exists with given Mobile Number : "+ mobileNumber));
		
		var loanDto = new LoanDto();
		BeanUtils.copyProperties(loan, loanDto);
		return loanDto;
	}

	@Override
	public boolean updateLoan(LoanDto loanDto) {
		log.info("LoanServiceImpl :: updateLoan");
		Loan loan = loanRepository.findByloanNumber(loanDto.getLoanNumber())
				.orElseThrow(()-> new ResourceNotFoundException("Loan Does not Exists with given Loan Number : "+ loanDto.getLoanNumber()));
		
		BeanUtils.copyProperties(loanDto, loan);
		loanRepository.save(loan);
		log.info("LoanServiceImpl :: updateLoan - loan updated successfully");
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		log.info("LoanServiceImpl :: deleteLoan");
		// TODO Auto-generated method stub
		Loan loan = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(()-> new ResourceNotFoundException("Loan Does not Exists with given Mobile Number : "+ mobileNumber));
		
		loanRepository.delete(loan);
		log.info("LoanServiceImpl :: deleteLoan - deleted loan successfully");
		return true;
	}

}
