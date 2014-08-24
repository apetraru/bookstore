package com.bk.model;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ROLE")
public class Role extends AbstractEntity {
	private String role;

	@OneToMany(cascade= CascadeType.ALL)
	@JoinTable(name="customer_role",
			joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="customer_id", referencedColumnName="id")}
	)
	private Set<Customer> userRoles;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
