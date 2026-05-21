package com.bank.bankmanagement.service;

import com.bank.bankmanagement.model.Customer;
import com.bank.bankmanagement.model.Loan;
import com.bank.bankmanagement.model.LoanStatus;
import com.bank.bankmanagement.repository.CustomerRepo;
import com.bank.bankmanagement.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public Loan applyLoan(Long customerId, double amount, double rate, int duration) {

        Customer customer = customerRepo.findById(customerId).orElseThrow();

        double emi = calculateEMI(amount, rate, duration);

        Loan loan = new Loan();
        loan.setAmount(amount);
        loan.setInterestRate(rate);
        loan.setDuration(duration);
        loan.setEmi(emi);
        loan.setRemainingBalance(amount);
        loan.setStatus(LoanStatus.PENDING);
        loan.setCreatedAt(LocalDateTime.now());
        loan.setCustomer(customer);

        return loanRepo.save(loan);
    }

    public Loan approveLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.APPROVED);
        return loanRepo.save(loan);
    }

    public Loan rejectLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.REJECTED);
        return loanRepo.save(loan);
    }

    public String payLoan(Long loanId, double amount) {
        Loan loan = loanRepo.findById(loanId).orElseThrow();

        if (loan.getStatus() != LoanStatus.APPROVED) {
            return "Loan not approved";
        }

        loan.setRemainingBalance(loan.getRemainingBalance() - amount);

        if (loan.getRemainingBalance() <= 0) {
            loan.setRemainingBalance(0);
            loan.setStatus(LoanStatus.CLOSED);
        }

        loanRepo.save(loan);
        return "Payment successful. Remaining balance: " + loan.getRemainingBalance();
    }

    public double getRemainingBalance(Long loanId) {
        return loanRepo.findById(loanId).orElseThrow().getRemainingBalance();
    }

//    public List<Loan> getLoansByCustomer(Long customerId) {
//        return loanRepo.findAll()
//                .stream()
//                .filter(l -> l.getCustomer().getId().equals(customerId))
//                .toList();
//    }
    public List<Loan> getLoansByCustomer(Long customerId) {
        return loanRepo.findByCustomer_Id(customerId);
    }

    private double calculateEMI(double principal, double rate, int time) {
        double monthlyRate = rate / (12 * 100);
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, time)) /
                (Math.pow(1 + monthlyRate, time) - 1);
    }
}