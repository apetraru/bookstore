package com.bk.bean;

import com.bk.model.Address;
import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import com.bk.repository.CustomerRepository;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ph
 * Date: 12/26/12
 */

@Component
@Scope("session")
public class CustomerBean {
    @Autowired
    CustomerRepository repository;

    @PersistenceContext
    EntityManager em;

    private Customer customer;

    @PostConstruct
    public void init() {
        customer = new Customer("alex", "ionel");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void findCustomer() {
        EmailAddress emailAddress = new EmailAddress("andrei@yahoo.com");
        Address address = new Address("ciornei", "iasi", "romania");
        customer.setEmailAddress(emailAddress);
        customer.add(address);
		customer = repository.save(customer);

    }

}
