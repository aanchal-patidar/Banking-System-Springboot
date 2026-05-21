package com.bank.bankmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotNull(message = "Name required")
    private String name;

    @Email
    private String email;

    @Size(min = 10, max = 10)
    private String phone;
}