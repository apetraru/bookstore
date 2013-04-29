package com.bk.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrei Petraru
 * Apr 27, 2013
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractIntegrationTest {
	@PersistenceContext
	private EntityManager entityManager;

	// If RESTART IDENTITY is specified, all table IDENTITY sequences and
	// all SEQUENCE objects in the schema are reset to their start values
	protected void reset() {
		entityManager.createNativeQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
