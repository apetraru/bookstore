package com.bk.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Book;
import com.bk.repository.BookRepository;
import com.bk.service.BookService;
import com.bk.util.PaginatedHibernateSearch;

/**
 * User: ph
 * Date: 1/28/13
 */

@Service
public class BookServiceImpl implements BookService {
	
	private static final float THRESHOLD = 0.75f;

    @Autowired
    private BookRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public PaginatedHibernateSearch<Book> search(String searchTerm, int firstResult, int resultsPerPage) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Book.class).get();
        Query query = queryBuilder.keyword()
            .fuzzy()
            .withThreshold(THRESHOLD)
            .onFields("title", "author.name")
            .matching(searchTerm)
            .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);
        fullTextQuery.setFirstResult(firstResult);
        fullTextQuery.setMaxResults(resultsPerPage);
        
        PaginatedHibernateSearch<Book> results = new PaginatedHibernateSearch<>();
        results.setResults(fullTextQuery.getResultList());
        results.setResultsSize(fullTextQuery.getResultSize());

        return results;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id);
    }

	@Override
	public Long count() {
		return repository.count();
	}

}
