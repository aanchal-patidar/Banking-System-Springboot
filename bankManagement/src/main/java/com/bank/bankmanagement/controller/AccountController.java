package com.bank.bankmanagement.controller;

import com.bank.bankmanagement.dto.AccountRequestDTO;
import com.bank.bankmanagement.dto.AccountResponseDTO;
import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService service;

    //create account
    @PostMapping("/create")
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO request) {
        return service.createAccount(request);
    }

    // READ (GET ALL)
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    // READ (GET BY ID)
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return service.getAccountById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Account updateAccount(@PathVariable Long id,
                                 @RequestBody Account account) {
        return service.updateAccount(id, account);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
        return "Account deleted successfully";
    }
}