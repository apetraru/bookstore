package com.bk.repository;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ph
 */

@Transactional(readOnly = true)
public interface CustomerRepository extends Repository<Customer, Long>, QueryDslPredicateExecutor<Customer> {

    Customer findById(Long id);

    @Transactional
    Customer save(Customer customer);

    Customer findByEmailAddress(EmailAddress emailAddress);

    @Query(value = "SELECT c FROM Customer c WHERE LOWER(c.firstname) LIKE :searchTerm OR LOWER(c.lastname) LIKE :searchTerm")
    public List<Customer> findCustomers(@Param("searchTerm") String searchTerm);
}
