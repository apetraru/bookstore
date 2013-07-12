package com.bk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;
import com.bk.model.Shelf;

/**
 * @author Andrei Petraru
 * 9 Jul 2013
 */

@Transactional(readOnly = true)
public interface ShelfRepository extends Repository<Shelf, Long>, Serializable{
	@Transactional
	Shelf save(Shelf shelf);
	
	@Query("select s from Shelf r where r.book = :book and r.customer = :customer")
    Review getCustomerShelf(@Param("book")Book book, @Param("customer") Customer customer);
}
