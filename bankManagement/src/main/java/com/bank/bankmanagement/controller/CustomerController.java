package com.bank.bankmanagement.controller;

import com.bank.bankmanagement.dto.CustomerDTO;
import com.bank.bankmanagement.model.Customer;
import com.bank.bankmanagement.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // GET ALL
    @GetMapping("/all")
    public List<Customer> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Customer deleted";
    }

    @PostMapping("/create")
    public Customer create(@Valid @RequestBody CustomerDTO dto)
    {
        return service.createCustomer(dto);
    }
}
