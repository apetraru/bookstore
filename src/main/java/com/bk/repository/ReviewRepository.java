package com.bk.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;

/**
 * @author Andrei Petraru
 * Date: 3/13/13
 */
@Transactional(readOnly = true)
public interface ReviewRepository extends Repository<Review, Long> {

    @Transactional
    Review save(Review review);

    @Query("select AVG(r.rating) from Review r where r.book = :book")
    Double getBookRating(@Param("book") Book book);

    @Query("select r from Review r where r.book = :book and r.customer = :customer")
    Review getCustomerRating(@Param("book")Book book, @Param("customer") Customer customer);

	@Query("select count(r) from Review r where r.book = :book and r.rating != null")
	Long getNumberOfBookRatings(@Param("book") Book book);
}
