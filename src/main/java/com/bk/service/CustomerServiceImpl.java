package com.bk.service;

import com.bk.model.Customer;
import com.bk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: ph
 * Date: 1/5/13
 */

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Customer findByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    @Override
    public Customer findByUsername(String username) {
        return repository.findByUsername(username);
    }

}
