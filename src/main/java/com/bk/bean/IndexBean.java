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
	private String searchTerm;
	private long timer;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public void search() {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException ex) {
			Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Book.class).get();
		Query query = queryBuilder.keyword().onFields("title", "author.name").matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);

		timer = System.currentTimeMillis();
		books = jpaQuery.getResultList();
		timer = System.currentTimeMillis() - timer;
	}

	public String getResult() {
		if (books.isEmpty()) {
			return "No results found";
		}

		StringBuilder builder = new StringBuilder();
		for (Book book : books) {
			builder.append(book.getTitle());
			builder.append(" by ");
			builder.append(book.getAuthor().getName());
			builder.append(" | in ");
		}

		builder.append(Long.toString(timer));
		builder.append(" ms");
		return builder.toString();
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
