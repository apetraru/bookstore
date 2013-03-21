package com.bk.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Book;

/**
 * User: Andrei Petraru
 * Date: 28 Jan 2013
 */

@Transactional(readOnly = true)
public interface BookRepository extends Repository<Book, Long>, QueryDslPredicateExecutor<Book> {

	Book findByIsbn(String isbn);

	@Transactional
	Book save(Book book);

	Book findById(Long id);

	Long count();

}
