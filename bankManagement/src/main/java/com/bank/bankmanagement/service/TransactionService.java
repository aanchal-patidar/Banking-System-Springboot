package com.bank.bankmanagement.service;

import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.model.Transaction;
import com.bank.bankmanagement.repository.AccountRepo;
import com.bank.bankmanagement.repository.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountRepo accountRepo;

    // ================== DEPOSIT ==================
    @Transactional
    public void deposit(String accountNumber, double amount) {

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction txn = new Transaction();
        txn.setType("DEPOSIT");
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());
        txn.setAccount(account);

        transactionRepo.save(txn);
    }

    // ================== WITHDRAW ==================
    @Transactional
    public void withdraw(String accountNumber, double amount) {

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction txn = new Transaction();
        txn.setType("WITHDRAW");
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());
        txn.setAccount(account);

        transactionRepo.save(txn);

    }

    // ================== TRANSFER ==================
    @Transactional
    public void transfer(String from, String to, double amount) {

        Account sender = accountRepo.findByAccountNumber(from)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account receiver = accountRepo.findByAccountNumber(to)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // update balances
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        accountRepo.save(sender);
        accountRepo.save(receiver);

        // sender transaction
        Transaction debit = new Transaction();
        debit.setType("TRANSFER");
        debit.setAmount(amount);
        debit.setDate(LocalDateTime.now());
        debit.setAccount(sender);
        debit.setReferenceAccount(to);

        // receiver transaction
        Transaction credit = new Transaction();
        credit.setType("TRANSFER");
        credit.setAmount(amount);
        credit.setDate(LocalDateTime.now());
        credit.setAccount(receiver);
        credit.setReferenceAccount(from);

        transactionRepo.save(debit);
        transactionRepo.save(credit);

    }

    // ================== HISTORY ==================
    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }

    public List<Transaction> getByAccount(String accountNumber) {
        return transactionRepo.findByAccountAccountNumber(accountNumber);
    }
}