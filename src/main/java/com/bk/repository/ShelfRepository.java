package com.bk.repository;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Shelf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Andrei Petraru
 * 9 Jul 2013
 */

@Transactional(readOnly = true)
public interface ShelfRepository extends Repository<Shelf, Long>, Serializable{
	@Transactional
	Shelf save(Shelf shelf);
	
	@Query("select s from Shelf s where s.book = :book and s.customer = :customer")
    Shelf getCustomerShelf(@Param("book")Book book, @Param("customer") Customer customer);
}
