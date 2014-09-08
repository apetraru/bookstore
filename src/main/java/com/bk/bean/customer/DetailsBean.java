package com.bk.bean.customer;

import static com.bk.util.Message.error;
import static com.bk.util.Message.globalError;
import static com.bk.util.Message.info;
import static com.bk.util.Message.msg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.bean.SessionBean;
import com.bk.model.Customer;
import com.bk.service.CustomerService;

@Component
@Scope("view")
public class DetailsBean {
	@Autowired private CustomerService customerService;
	@Autowired private SessionBean sessionBean;

	private Customer customer;

	@PostConstruct
	public void init() {
		customer = sessionBean.getLoggedInUser();
		if (customer == null) {
			globalError(msg("loginFirst"));
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void update() {
		if (usernameExists()) {
			error("table:profileForm:username", msg("usernameTaken"));
			return;
		}

		if (emailExists()) {
			error("table:profileForm:email", msg("emailTaken"));
			return;
		}

		customer = customerService.save(customer);
		info("table:profileForm:updateButton", msg("detailsUpdated"));
	}

	private boolean usernameExists() {
		Customer duplicateCustomer = customerService.findByUsername(customer
				.getUsername());
		if (duplicateCustomer == null) {
			return false;
		}
		
		return (customer.getUsername().equals(duplicateCustomer.getUsername()) && !duplicateCustomer
				.getId().equals(customer.getId()));
	}

	private boolean emailExists() {
		Customer duplicateCustomer = customerService.findByEmailAddress(customer
				.getEmailAddress());
		if (duplicateCustomer == null) {
			return false;
		}
		
		return (customer.getEmailAddress().equals(duplicateCustomer.getEmailAddress()) && !duplicateCustomer
				.getId().equals(customer.getId()));
	}

}
