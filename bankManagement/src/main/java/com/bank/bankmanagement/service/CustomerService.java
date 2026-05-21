package com.bank.bankmanagement.service;

import com.bank.bankmanagement.dto.CustomerDTO;
import com.bank.bankmanagement.model.Customer;
import com.bank.bankmanagement.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo repo;

    public Customer createCustomer(CustomerDTO dto) {
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        return repo.save(c);
    }

    public List<Customer> getAll() {
        return  repo.findAll();
    }

    public Customer getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer update(Long id, Customer updated) {
        Customer c = getById(id);
        c.setName(updated.getName());
        c.setEmail(updated.getEmail());
        c.setPhone(updated.getPhone());
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
