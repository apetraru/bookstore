package com.bk.repository;

import com.bk.model.Book;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ph
 * Date: 1/28/13
 */

@Transactional(readOnly = true)
public interface BookRepository extends Repository<Book, Long>, QueryDslPredicateExecutor<Book> {

    Book findByIsbn(String isbn);

    @Transactional
    Book save(Book book);
}
