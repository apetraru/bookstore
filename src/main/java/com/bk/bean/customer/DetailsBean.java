package com.bk.bean.customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.bean.SessionBean;
import com.bk.model.Customer;
import com.bk.service.CustomerService;
import com.bk.util.Message;

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
			String message = "Please login first to view your profile";
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									message, null));
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
			Message.addMessage("table:profileForm:username",
					"Username already exists", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (emailExists()) {
			Message.addMessage("table:profileForm:email", "Email already exists",
					FacesMessage.SEVERITY_ERROR);
			return;
		}

		customer = customerService.save(customer);
		Message.addMessage("table:profileForm:updateButton",
				"Details updated successfully !", FacesMessage.SEVERITY_INFO);
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
