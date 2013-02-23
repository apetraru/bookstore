/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.bean;

import com.bk.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ph
 */
@Component
public class IndexBean {

	private List<Book> books = new ArrayList<>();

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void search() {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException ex) {
			Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Book.class).get();
		Query query = queryBuilder.keyword().onFields("title", "author.name").matching("gigi").createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);

		books = jpaQuery.getResultList();
	}

	public String getResult() {
		StringBuilder builder = new StringBuilder();
		for (Book book : books) {
			builder.append(book.getTitle());
			builder.append(" by ");
			builder.append(book.getAuthor().getName());
			builder.append(" | ");
		}
		return builder.toString();
	}
}
