package com.bk.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.model.Customer;

@Component
@Scope("session")
public class SessionBean {
	private Customer loggedInUser;
	private boolean loggedOn;

	public Customer getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Customer loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public boolean isLoggedOn() {
		return loggedOn;
	}

	public void setLoggedOn(boolean loggedOn) {
		this.loggedOn = loggedOn;
	}

}
