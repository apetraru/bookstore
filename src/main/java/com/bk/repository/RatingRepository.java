package com.bk.repository;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrei Petraru
 * Date: 3/13/13
 */
@Transactional(readOnly = true)
public interface RatingRepository extends Repository<Rating, Long> {

    @Transactional
    Rating save(Rating rating);

    @Transactional
    Rating delete(Rating rating);

    @Query("select AVG(r.rating) from Rating r where r.book = :book")
    Integer getBookRating(@Param("book") Book book);

    @Query("select r.rating from Rating r where r.book = :book and r.customer = :customer")
    Integer getCustomerRating(@Param("book")Book book, @Param("customer") Customer customer);
}
