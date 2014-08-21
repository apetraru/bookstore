package com.bk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bk.model.Customer;
import com.bk.service.CustomerService;

public class CustomerDetailsService implements UserDetailsService {

	@Autowired private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Customer customer = customerService.findByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException("Not found");
		}
		CustomerDetails customerDetails = new CustomerDetails(customer);
		return customerDetails;
	}

}
