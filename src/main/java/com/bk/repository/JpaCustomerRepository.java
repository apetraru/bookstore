package com.bk.repository;

import com.bk.model.Customer;
import com.bk.model.EmailAddress;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ph
 */
@Repository
public class JpaCustomerRepository implements CustomerRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		if (customer.getId() == null) {
			em.persist(customer);
			return customer;
		} else {
			return em.merge(customer);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findByEmailAddress(EmailAddress emailAddress) {
		TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.emailAddress = :email",
				Customer.class);
		query.setParameter("email", emailAddress);

		return query.getSingleResult();
	}
}
