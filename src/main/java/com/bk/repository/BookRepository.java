package com.bk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Book;

/**
 * User: ph
 * Date: 1/28/13
 */

@Transactional(readOnly = true)
public interface BookRepository extends Repository<Book, Long>{

    Book findByIsbn(String isbn);

    @Transactional
    Book save(Book book);

    Book findById(Long id);
    
    Long count();
    
    Page<Book> findAll(Pageable pageable);
}
