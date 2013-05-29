package com.bk.service;

import java.io.Serializable;

import com.bk.model.Customer;

/**
 * @author Andrei Petraru
 * Date: 1/5/13
 */

public interface CustomerService extends Serializable {

    Customer save(Customer customer);

    Customer findById(Long id);

    Customer findByEmailAddress(String emailAddress);

    Customer findByUsername(String username);

}
