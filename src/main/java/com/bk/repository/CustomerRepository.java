package com.bk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;

/**
 * @author Andrei Petraru
 */

@Transactional(readOnly = true)
public interface CustomerRepository extends Repository<Customer, Long>, QueryDslPredicateExecutor<Customer> {

    Customer findById(Long id);

    @Transactional
    Customer save(Customer customer);

    Customer findByEmailAddress(EmailAddress emailAddress);

    Customer findByUsername(String username);

    @Query(value = "SELECT c FROM Customer c WHERE LOWER(c.firstname) LIKE :searchTerm OR LOWER(c.lastname) LIKE :searchTerm")
    List<Customer> findCustomers(@Param("searchTerm") String searchTerm);
}
