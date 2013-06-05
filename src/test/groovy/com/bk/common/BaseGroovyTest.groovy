package com.bk.common

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

import spock.lang.Specification

/**
 * @author Andrei Petraru
 * 5 Jun 2013
 */

@ContextConfiguration(locations = "/test-context.xml")
@Transactional
class BaseGroovyTest extends Specification {
	@PersistenceContext
	EntityManager entityManager;

	// If RESTART IDENTITY is specified, all table IDENTITY sequences and
	// all SEQUENCE objects in the schema are reset to their start values
	def setup() {
		entityManager.createNativeQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate()
	}
}
