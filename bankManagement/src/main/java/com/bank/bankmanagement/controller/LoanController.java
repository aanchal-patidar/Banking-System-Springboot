package com.bank.bankmanagement.controller;

import com.bank.bankmanagement.model.Loan;
import com.bank.bankmanagement.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping("/apply")
    public Loan apply(@RequestParam Long customerId,
                      @RequestParam double amount,
                      @RequestParam double rate,
                      @RequestParam int duration) {

        return service.applyLoan(customerId, amount, rate, duration);
    }

    @PostMapping("/approve/{loanId}")
    public Loan approve(@PathVariable Long loanId) {
        return service.approveLoan(loanId);
    }

    @PostMapping("/reject/{loanId}")
    public Loan reject(@PathVariable Long loanId) {
        return service.rejectLoan(loanId);
    }

    @PostMapping("/pay")
    public String pay(@RequestParam Long loanId,
                      @RequestParam double amount) {
        return service.payLoan(loanId, amount);
    }

    @GetMapping("/balance/{loanId}")
    public double balance(@PathVariable Long loanId) {
        return service.getRemainingBalance(loanId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Loan> customerLoans(@PathVariable Long customerId) {
        return service.getLoansByCustomer(customerId);
    }
}