package com.bk.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bk.enums.Status;

/**
 * @author Andrei Petraru
 * 9 Jul 2013
 */

@Entity
@Table(name = "SHELF")
public class Shelf extends AbstractEntity {
	private static final long serialVersionUID = -2054868769746721425L;

	private Status status;

	@OneToMany
	private Set<Book> shelf = new HashSet<>();
	private Date startDate;
	private Date endDate;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Customer customer;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void addBookToShelf(Book book) {
		this.shelf.add(book);
	}

	public Set<Book> getShelf() {
		return Collections.unmodifiableSet(shelf);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
