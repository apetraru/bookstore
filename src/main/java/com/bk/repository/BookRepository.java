package com.bk.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Author;
import com.bk.model.Book;

/**
 * @author Andrei Petraru
 * Date: 28 Jan 2013
 */

@Transactional(readOnly = true)
public interface BookRepository extends Repository<Book, Long>, QueryDslPredicateExecutor<Book>, Serializable {

	Book findByIsbn(String isbn);

	@Transactional
	Book save(Book book);

	Book findById(Long id);

	Long count();
	
	Page<Book> findByAuthor(Author author, Pageable pageable);

}
