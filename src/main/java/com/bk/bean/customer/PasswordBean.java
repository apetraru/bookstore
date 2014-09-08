package com.bk.bean.customer;

import static com.bk.util.Message.error;
import static com.bk.util.Message.globalError;
import static com.bk.util.Message.info;
import static com.bk.util.Message.msg;

import javax.annotation.PostConstruct;

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
	private static final String UPDATE_PASSWORD = "table:passwordForm:updateButton";
	
	@Autowired private CustomerService customerService;
	@Autowired private SessionBean sessionBean;
	@Autowired private ShaPasswordEncoder passwordEncoder;

	private String oldPassword;
	private String newPassword;
	private Customer customer;

	@PostConstruct
	public void init() {
		customer = sessionBean.getLoggedInUser();
		if (customer == null) {
			globalError(msg("loginFirst"));
		}
	}

	public void update() {
		String hashedPassword = passwordEncoder.encodePassword(oldPassword, null);
		if (!hashedPassword.equals(customer.getPassword())) {
			error(UPDATE_PASSWORD, msg("incorrectPassword"));
			return;
		}
		hashedPassword = passwordEncoder.encodePassword(newPassword, null);
		customer.setPassword(hashedPassword);
		customer = customerService.save(customer);
		info(UPDATE_PASSWORD, msg("passwordUpdated"));
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
