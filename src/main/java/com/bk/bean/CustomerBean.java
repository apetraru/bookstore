package com.bk.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.model.Customer;
import com.bk.service.CustomerService;
import com.bk.util.Message;

@Component
@Scope("view")
public class CustomerBean {
	@Autowired private CustomerService customerService;
	@Autowired private SessionBean sessionBean;

	private Customer customer;
	
	@PostConstruct
	public void init() {
		customer = sessionBean.getLoggedInUser();
		if (customer == null) {
			String message = "Please login first to view your profile";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void update() {
		customer = customerService.save(customer);
		Message.addMessage("profileForm:updateButton", "Details updated successfully !", FacesMessage.SEVERITY_INFO);
	}

}
