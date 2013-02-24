/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.bean;

import com.bk.model.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ph
 */
@Component
@Scope("request")
public class IndexBean {

    private List<Book> books = new ArrayList<>();
    private String searchTerm;
    private long timer;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        index();
    }

    @Transactional(readOnly = true)
    public void search() {

        timer = System.currentTimeMillis();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Book.class).get();
        Query query = queryBuilder.keyword()
            .onFields("title", "author.name")
            .matching(searchTerm)
            .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);

        books = jpaQuery.getResultList();
        timer = System.currentTimeMillis() - timer;
    }

    private void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public long getTimer() {
        return timer;
    }
}
