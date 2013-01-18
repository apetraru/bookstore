package com.bk.service;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import com.bk.predicate.CustomerPredicate;
import com.bk.repository.CustomerRepository;
import com.mysema.query.types.Predicate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * User: ph
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

    public Customer findByEmailAddress(EmailAddress emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    public Customer findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<Customer> search(String searchTerm) {
        return repository.findCustomers(searchTerm);
    }

    public List<Customer> searchQueryDsl(String searchTerm) {
        Predicate customerPredicate = CustomerPredicate.firstOrLastNameStartsWith(searchTerm);

        Iterable<Customer> customers = repository.findAll(customerPredicate);
        List<Customer> customerList = new ArrayList<>();
        CollectionUtils.addAll(customerList, customers.iterator());

        return customerList;
    }

}
