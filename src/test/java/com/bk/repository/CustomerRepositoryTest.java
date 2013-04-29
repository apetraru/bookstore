package com.bk.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bk.common.AbstractIntegrationTest;
import com.bk.model.Customer;

/**
 * @author: Andrei Petraru
 * Date: 4/28/13
 */
public class CustomerRepositoryTest extends AbstractIntegrationTest {
    private static final String USERNAME = "user";
    private static final String NEW_USERNAME = "user2";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email@email.com";
    private static final String NEW_EMAIL = "newemail@email.com";
    private static final Long ID = 1L;

    @Autowired
    private CustomerRepository repository;

    @Before
    public void setUp() {
    	reset();
        Customer customer = new Customer();
        customer.setUsername(USERNAME);
        customer.setPassword(PASSWORD);
        customer.setEmailAddress(EMAIL);
        repository.save(customer);
    }

    @Test
    public void findByIdTest() {
        Customer customer = repository.findById(ID);
        assertEquals(customer.getId(), ID);
    }

    @Test
    public void findByEmailAddressTest() {
        Customer customer = repository.findByEmailAddress(EMAIL);
        assertNotNull(customer);
    }

    @Test
    public void findByUsernameTest() {
        Customer customer = repository.findByUsername(USERNAME);
        assertNotNull(customer);
    }

    @Test
    public void saveTestOk() {
        Customer customer = new Customer();
        customer.setUsername(NEW_USERNAME);
        customer.setPassword(PASSWORD);
        customer.setEmailAddress(NEW_EMAIL);
        customer = repository.save(customer);
        assertNotNull(customer);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void duplicateUserSaveTestFail() {
        Customer customer = new Customer();
        customer.setUsername(USERNAME);
        customer.setPassword(PASSWORD);
        customer.setEmailAddress(NEW_EMAIL);
        customer = repository.save(customer);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void duplicateEmailSaveTestFail() {
        Customer customer = new Customer();
        customer.setUsername(NEW_USERNAME);
        customer.setPassword(PASSWORD);
        customer.setEmailAddress(EMAIL);
        customer = repository.save(customer);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void emptyUserSaveTestFail() {
        Customer customer = new Customer();
        repository.save(customer);
    }

}
