package com.bk.repository

import org.springframework.beans.factory.annotation.Autowired;

import com.bk.common.BaseGroovyTest;
import com.bk.model.Customer;
import org.springframework.dao.DataIntegrityViolationException

/**
 * @author Andrei Petraru
 * 6 Jun 2013
 */
class CustomerRepositoryGroovyTest extends BaseGroovyTest{
	@Autowired
	CustomerRepository repository

	String user = 'user'
	String pass = 'pass'
	String email = 'email@email.com'

	def setup() {
		Customer customer = new Customer(username: user, password: pass, emailAddress: email)
		repository.save(customer)
	}

	def 'save customer'() {
		when:
		Customer customer = new Customer(username: 'new_user', password: pass, emailAddress: 'new_email@email.com')
		customer = repository.save(customer)

		then:
		customer != null
	}

	def 'save customer with duplicate username throws DataIntegrityViolationException'() {
		when:
		Customer customer = new Customer(username: 'user', password: 'pass', emailAddress: 'new_email@email.com')
		customer = repository.save(customer)

		then:
		thrown DataIntegrityViolationException
	}

	def 'save customer with duplicate email throws DataIntegrityViolationException'() {
		when:
		Customer customer = new Customer(username: 'new_user', password: 'pass', emailAddress: 'email@email.com')
		customer = repository.save(customer)

		then:
		thrown DataIntegrityViolationException
	}

	def 'find customer by ID'() {
		expect:
		repository.findById(1L).id == 1L
	}

	def 'find customer by non existing ID'() {
		expect:
		repository.findById(2L) == null
	}

	def 'find customer by username'() {
		expect:
		repository.findByUsername('user').username == 'user'
	}

	def 'find customer by non existing username'() {
		expect:
		repository.findByUsername('no user') == null
	}

	def 'find customer by email'() {
		expect:
		repository.findByEmailAddress(email).emailAddress == email
	}
	
	def 'find customer by non existing email'() {
		expect:
		repository.findByEmailAddress('no email') == null
	}
}
