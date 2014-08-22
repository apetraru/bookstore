package com.bk.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bk.security.CustomerAuthenticationService;
import com.bk.service.CustomerService;
import com.bk.util.Message;

/**
 * @author Andrei Petraru
 * Date: 1/15/13
 */

@Component
@Scope("view")
public class LoginBean implements Serializable {
	@Autowired private CustomerService customerService;
	@Autowired private CustomerAuthenticationService customerAuthenticationService;
	@Autowired private SessionBean sessionBean;

	private String username;
	private String password;

	public String login() {
		try {
			sessionBean.setLoggedInUser(customerAuthenticationService.authenticateUser(
					username, password));
			sessionBean.setLoggedOn(true);
			return NavigationBean.home();

		} catch (AuthenticationException e) {
			addErrorMessage();
			return null;
		}
	}

	public String logout() {
		sessionBean.setLoggedOn(false);
		SecurityContextHolder.clearContext();
		return NavigationBean.home();
	}

	private void addErrorMessage() {
		Message.addMessage("loginButton", "Incorrect username or password",
				FacesMessage.SEVERITY_ERROR);
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
}
