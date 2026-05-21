package com.bank.bankmanagement.service;

import com.bank.bankmanagement.dto.AccountRequestDTO;
import com.bank.bankmanagement.dto.AccountResponseDTO;
import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.model.Customer;
import com.bank.bankmanagement.model.Transaction;
import com.bank.bankmanagement.repository.AccountRepo;
import com.bank.bankmanagement.repository.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepo repo;

    @Autowired
    private CustomerRepo customerRepo;

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }

    //  CREATE ACCOUNT
    @Transactional
    public AccountResponseDTO createAccount(AccountRequestDTO request) {

        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(request.getBalance());
        account.setCustomer(customer);

        customer.getAccounts().add(account);
        Account saved = repo.save(account);

        return new AccountResponseDTO(
                saved.getId(),
                saved.getAccountNumber(),
                saved.getBalance()
        );
    }

    //  GET ALL
    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    //  GET BY ID
    public Account getAccountById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    //  UPDATE
    public Account updateAccount(Long id, Account updatedAccount) {
        Account account = getAccountById(id);

        account.setBalance(updatedAccount.getBalance());
        //account.setAccountNumber(updatedAccount.getAccountNumber()); //not allowed
        return repo.save(account);
    }

    // DELETE
    public void deleteAccount(Long id) {
        repo.deleteById(id);
    }


}