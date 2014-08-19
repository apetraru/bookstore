package com.bk.service;

import com.bk.model.Customer;
import com.bk.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Andrei Petraru
 * Date: 1/5/13
 */

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer findById(Long id) {
        return repository.findById(id);
    }

    public Customer findByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    public Customer findByUsername(String username) {
        return repository.findByUsername(username);
    }

}
