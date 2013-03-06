package com.bk.bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 06/03/2013
 *
 * @author Andrei Petraru
 */
@Component
@Lazy(false)
public class IndexBean {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void index() {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException ex) {
			Logger.getLogger(BookBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
