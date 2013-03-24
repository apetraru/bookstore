package com.bk.service;

import com.bk.model.Book;
import com.bk.util.PaginatedHibernateSearch;

/**
 * User: ph
 * Date: 1/28/13
 */
public interface BookService {
    PaginatedHibernateSearch<Book> search(String searchTerm, int firstResult, int resultsPerPage);

    Book save(Book book);

    Book findById(Long id);
    
    Long count();
}
