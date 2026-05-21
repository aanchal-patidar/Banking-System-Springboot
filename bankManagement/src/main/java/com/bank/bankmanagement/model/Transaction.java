package com.bank.bankmanagement.model;

import com.bank.bankmanagement.model.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // DEPOSIT, WITHDRAW, TRANSFER

    private double amount;

    private LocalDateTime date;

    @ManyToOne
    private Account account; // primary account

    private String referenceAccount; // for transfer (receiver/sender)
}