package com.bank.bankmanagement.controller;

import com.bank.bankmanagement.model.Transaction;
import com.bank.bankmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    //  DEPOSIT
    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber,
                          @RequestParam double amount) {

        service.deposit(accountNumber, amount);
        return "Deposit successful";
    }

    // WITHDRAW
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber,
                           @RequestParam double amount) {

        service.withdraw(accountNumber, amount);
        return "Withdraw successful";
    }

    // TRANSFER
    @PostMapping("/transfer")
    public String transfer(@RequestParam String from,
                           @RequestParam String to,
                           @RequestParam double amount) {

        service.transfer(from, to, amount);
        return "Transfer successful";
    }

    // ALL HISTORY
    @GetMapping("/all")
    public List<Transaction> getAll() {
        return service.getAll();
    }

    // ACCOUNT HISTORY
    @GetMapping("/{accountNumber}")
    public List<Transaction> getByAccount(@PathVariable String accountNumber) {
        return service.getByAccount(accountNumber);
    }
}