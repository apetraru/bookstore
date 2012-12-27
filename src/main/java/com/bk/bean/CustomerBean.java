package com.bk.bean;

import com.bk.model.Address;
import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import com.bk.repository.CustomerRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ph Date: 12/26/12
 */
@Component
@Scope("session")
public class CustomerBean {

	@Autowired
	CustomerRepository repository;

	private Customer addedCustomer;
	private Customer foundCustomer;

	@PostConstruct
	public void init() {
		addedCustomer = new Customer("c1", "c1");
		foundCustomer = new Customer("c2", "c2");
	}

	public Customer getAddedCustomer() {
		return addedCustomer;
	}

	public void setAddedCustomer(Customer addedCustomer) {
		this.addedCustomer = addedCustomer;
	}

	public Customer getFoundCustomer() {
		return foundCustomer;
	}

	public void setFoundCustomer(Customer foundCustomer) {
		this.foundCustomer = foundCustomer;
	}

	public void addCustomer() {
		EmailAddress emailAddress = new EmailAddress("andrei@yahoo.com");
		Address address = new Address("asd", "asd", "asd");
		addedCustomer.setEmailAddress(emailAddress);
		addedCustomer.add(address);
		addedCustomer = repository.save(addedCustomer);
	}

	public void findCustomer(Long id) {
		foundCustomer = repository.findById(id);
	}
}
