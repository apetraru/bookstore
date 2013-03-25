package com.bk.service;

import java.util.List;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;

/**
 * @author Andrei Petraru
 * Date: 1/5/13
 */

public interface CustomerService {

    Customer save(Customer customer);

    Customer findById(Long id);

    Customer findByEmailAddress(EmailAddress emailAddress);

    Customer findByUsername(String username);

    List<Customer> search(String searchTerm);

    List<Customer> searchQueryDsl(String searchTerm);

}
