package com.bk.service;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import java.util.List;

/**
 * User: ph
 * Date: 1/5/13
 */

public interface CustomerService {

    public Customer save(Customer customer);

    public Customer findById(Long id);

    public Customer findByEmailAddress(EmailAddress emailAddress);

    public Customer findByUsername(String username);

    public List<Customer> search(String searchTerm);

    public List<Customer> searchQueryDsl(String searchTerm);

}
