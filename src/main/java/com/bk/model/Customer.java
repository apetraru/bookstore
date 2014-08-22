package com.bk.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andrei Petraru
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractEntity {

	private String firstname;
	private String lastname;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true, name = "email_address")
	private String emailAddress;
	private String address;
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();

	public Customer(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Customer() {
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
