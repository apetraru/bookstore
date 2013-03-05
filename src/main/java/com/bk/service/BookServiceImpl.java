package com.bk.service;

import com.bk.model.Book;
import com.bk.repository.BookRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ph
 * Date: 1/28/13
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Book> search(String searchTerm, int firstResult, int maxResults) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Book.class).get();
        Query query = queryBuilder.keyword()
            .fuzzy()
            .withThreshold(0.7f)
            .onFields("title", "author.name")
            .matching(searchTerm)
            .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);
        jpaQuery.setFirstResult(firstResult);
        jpaQuery.setMaxResults(maxResults);

        return jpaQuery.getResultList();
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id);
    }

}
