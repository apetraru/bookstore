package com.bk.model;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Andrei Petraru
 */
@Embeddable
public class EmailAddress implements Serializable {

	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
	@Column(name = "email")
	private String value;

	public EmailAddress(String emailAddress) {
		if (isValid(emailAddress)) {
			this.value = emailAddress;
		}
	}

	protected EmailAddress() {
	}

	private boolean isValid(String candidate) {
		return (candidate != null && PATTERN.matcher(candidate).matches());
	}

	@Override
	public String toString() {
		return value;
	}
}
