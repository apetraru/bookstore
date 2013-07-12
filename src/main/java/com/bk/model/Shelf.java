package com.bk.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToOne;

import com.bk.enums.Status;

/**
 * @author Andrei Petraru 
 * 9 Jul 2013
 */
public class Shelf extends AbstractEntity {
	private Status status;
	private Set<Book> shelf = new HashSet<>();

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
}
