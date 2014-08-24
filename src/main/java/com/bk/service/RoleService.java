package com.bk.service;

import com.bk.model.Role;
import com.bk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andrei Petraru
 * 24 Aug 2014
 */

@Service
public class RoleService {
	@Autowired private RoleRepository repository;

	public Role getUserRole() {
		return repository.getByRole("USER");
	}

	public Role getAdminRole() {
		return repository.getByRole("ADMIN");
	}
}
