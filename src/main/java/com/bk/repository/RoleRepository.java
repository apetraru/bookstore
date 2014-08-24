package com.bk.repository;

import com.bk.model.Role;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Andrei Petraru
 * 24 Aug 2014
 */

@Transactional(readOnly = true)
public interface RoleRepository extends Repository<Role, Long>, Serializable{
	public Role getByRole(String role);
}
