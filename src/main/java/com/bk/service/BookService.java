package com.bk.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Author;
import com.bk.model.Book;
import com.bk.repository.BookRepository;
import com.bk.util.PaginatedHibernateSearch;
import com.mysema.query.types.Predicate;

import java.util.List;

/**
 * @author Andrei Petraru
 * Date: 1/28/13
 */

@Service
public class BookService {
	
	private static final float THRESHOLD = 0.75f;

    @Autowired private BookRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
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

    public Book save(Book book) {
        return repository.save(book);
    }

    public Book findById(Long id) {
        return repository.findById(id);
    }

	public Long count() {
		return repository.count();
	}
	
	public Book findByIsbn(String ISBN) {
		return repository.findByIsbn(ISBN);
	}
	
	public Page<Book> findByAuthor(Author author, Pageable pageable) {
		return repository.findByAuthor(author, pageable);
	}
	
	public Page<Book> findAll(Predicate predicate, Pageable pageable) {
		return repository.findAll(predicate, pageable);
	}

    public List<Book> findByIDs(List<Long> ids) {
        return repository.findByIDs(ids);
    }

    public List<Book> findByCustomerReview(Long customerId) {
        return repository.findByCustomerReview(customerId);
    }

    public List<Book> findRandom() {
        return repository.findRandom();
    }

}
