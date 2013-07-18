package com.bk.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bk.model.Author;
import com.bk.model.Book;
import com.bk.util.PaginatedHibernateSearch;

/**
 * @author Andrei Petraru
 * Date: 1/28/13
 */
public interface BookService extends Serializable {
    PaginatedHibernateSearch<Book> search(String searchTerm, int firstResult, int resultsPerPage);

    Book save(Book book);

    Book findById(Long id);
    
    Long count();
    
    Page<Book> findByAuthor(Author author, Pageable pageable);
}
