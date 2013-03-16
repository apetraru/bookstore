package com.bk.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * @author Andrei Petraru
 * Mar 15, 2013
 */

@Entity
public class Genre extends AbstractEntity {
	private String genre;

	@ManyToMany(mappedBy = "genres")
	private Set<Book> books = new HashSet<>();

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}
	
	public Set<Book> getBooks() {
		return Collections.unmodifiableSet(books);
	}
}
