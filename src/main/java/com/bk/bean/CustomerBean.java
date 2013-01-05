package com.bk.bean;

import com.bk.service.CustomerService;
import com.bk.model.Address;
import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import com.bk.predicate.CustomerPredicate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ph Date: 12/26/12
 */
@Component
@Scope("session")
public class CustomerBean {

	@Resource
    CustomerService customerService;

	private Customer addedCustomer;
	private Customer foundCustomer;
    private List<Customer> customers = new ArrayList<>();

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
		addedCustomer = customerService.save(addedCustomer);
	}

	public void findCustomer() {
        String search = "gigi";
        CustomerPredicate cp = new CustomerPredicate();
        customers = customerService.searchQueryDsl(search);
	}

    public String getListSize() {
        return String.valueOf(customers.size());
    }
}
