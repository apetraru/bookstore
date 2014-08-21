package com.bk.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bk.model.Customer;
import com.bk.security.CustomerAuthenticationService;
import com.bk.service.CustomerService;
import com.bk.util.Message;

/**
 * @author Andrei Petraru
 * Date: 1/15/13
 */

@Component
@Scope("session")
public class LoginBean implements Serializable {

	private boolean loggedOn = false;
	private String username;
	private String password;
	private Customer loggedInUser;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerAuthenticationService customerAuthenticationService;

	public String login() {
		try {
			loggedInUser = customerAuthenticationService.authenticateUser(
					username, password);
			loggedOn = true;
			return NavigationBean.home();

		} catch (AuthenticationException e) {
			addErrorMessage();
			return null;
		}
	}

	public String logout() {
		loggedOn = false;
		SecurityContextHolder.clearContext();
		return NavigationBean.home();
	}

	private void addErrorMessage() {
		Message.addMessage("loginButton", "Incorrect username or password",
				FacesMessage.SEVERITY_ERROR);
	}

	public boolean isLoggedOn() {
		return loggedOn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getLoggedInUser() {
		return loggedInUser;
	}
}
