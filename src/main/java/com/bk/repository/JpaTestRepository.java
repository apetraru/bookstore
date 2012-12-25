/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.repository;

import com.bk.model.Test;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ph
 */

@Repository
@Transactional
public class JpaTestRepository implements TestRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Test findById(Long id) {
		return em.find(Test.class, id);
	}

	@Override
	public Test save(Test test) {
		if (test.getId() == null) {
			em.persist(test);
			return test;
		}
		else {
			return em.merge(test);
		}
	}

}
