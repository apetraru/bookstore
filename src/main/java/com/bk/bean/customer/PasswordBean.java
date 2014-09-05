package com.bk.bean.customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bk.bean.SessionBean;
import com.bk.model.Customer;
import com.bk.service.CustomerService;

@Component
@Scope("view")
public class PasswordBean {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SessionBean sessionBean;
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	private String oldPassword;
	private String newPassword;
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

	public void update() {
		String hashedPassword = passwordEncoder.encodePassword(oldPassword,
				null);
		if (!hashedPassword.equals(customer.getPassword())) {
			FacesContext.getCurrentInstance().addMessage("table:passwordForm:updateButton", new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Incorrect password", null));
			return;
		}
		hashedPassword = passwordEncoder.encodePassword(newPassword, null);
		customer.setPassword(hashedPassword);
		customer = customerService.save(customer);
		FacesContext.getCurrentInstance().addMessage("table:passwordForm:updateButton", new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Password updated successfully", null));
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
