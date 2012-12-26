package com.bk.repository;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ph
 */
public interface CustomerRepository extends Repository<Customer, Long> {
	Customer findById(Long id);
	Customer save(Customer customer);
	Customer findByEmailAddress(EmailAddress emailAddress);
}
