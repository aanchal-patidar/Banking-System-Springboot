package com.bank.bankmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private double balance;

    @ManyToOne
    @JsonIgnore   // for ignoring infinite recursion in response
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
