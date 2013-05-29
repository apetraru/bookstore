package com.bk.service;

import java.io.Serializable;

import com.bk.model.Book;
import com.bk.util.PaginatedHibernateSearch;

/**
 * User: ph
 * Date: 1/28/13
 */
public interface BookService extends Serializable {
    PaginatedHibernateSearch<Book> search(String searchTerm, int firstResult, int resultsPerPage);

    Book save(Book book);

    Book findById(Long id);
    
    Long count();
}
