
/**
 * @author Andrei Petraru
 * Date: 3/13/13
 */
package com.bk.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Rating extends AbstractEntity implements Serializable {

    private Integer rating;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
