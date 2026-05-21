package com.bank.bankmanagement.repository;

import com.bank.bankmanagement.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomer_Id(Long customerId);
}